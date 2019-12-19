package com.softdough.grow.presentation.ui.Routine

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.model.*
import com.softdough.grow.domain.usecase.*
import com.softdough.grow.presentation.GlobalApplication
import com.softdough.grow.presentation.default
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class CategoryIdRoutineBinding(
    val categoryId: Long,
    val routines: List<RoutineItem>
)

abstract class RoutineViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase,
    private val setInfoUseCase: SetInfoUseCase
) : ViewModel() {

    //live entity
    val liveCategories = MutableLiveData<List<CategoryItem>>()

    val liveSetInfo = MutableLiveData<List<SetInfoItem>>()
    var setInfo: MutableList<SetInfoItem> = arrayListOf()

    val liveCategoryIdRoutineBindings = MutableLiveData<List<CategoryIdRoutineBinding>>()
    var categoryRoutineBindings: MutableList<CategoryIdRoutineBinding> = arrayListOf()

    //ui state
    val liveCategoryImageLoad = MutableLiveData<Boolean>().default(false)
    val liveRoutineImageLoad = MutableLiveData<Boolean>().default(false)

    var saveState = false
    val currentScroll = 0
    val currentPage = MutableLiveData<Int>().default(0)
    val categoryImages: MutableList<Bitmap> = arrayListOf()

    //etc
    val jwtToken = GlobalApplication.jwtToken
    val compositeDisposable = CompositeDisposable()

    //Add Function
    private fun addCategoryRoutineBinding(item: CategoryIdRoutineBinding) {
        categoryRoutineBindings.add(item)
        liveCategoryIdRoutineBindings.postValue(categoryRoutineBindings)
    }

    private fun resetCategoryRoutineBinding() {
        categoryRoutineBindings = arrayListOf()
    }

    private fun addSetInfo(item: SetInfoItem) {
        setInfo.add(item)
        liveSetInfo.postValue(setInfo)
    }

    private fun resetSetInfo() {
        setInfo = arrayListOf()
    }

    //Datasource Communication
    fun getCategory(type: String, cached: Boolean = true) {
        compositeDisposable.add(
            categoryUseCase.get(type, cached)
                .subscribeOn(Schedulers.io())
                .map {
                    it.map { getRoutine(it.id, false) }
                    it.mapToPresentation()
                }
                .subscribe({
                    liveCategories.postValue(it)
                }, { e -> println(e) })
        )
    }

    fun setCategory(type: String, name: String) {
        compositeDisposable.add(
            categoryUseCase.set(type, name)
                .subscribeOn(Schedulers.io())
                .map {

                    it.map { getRoutine(it.id, false) }
                    it.mapToPresentation()
                }
                .subscribe({ liveCategories.postValue(it) }, { e -> println(e) })
        )
    }

    fun getRoutine(categoryId: Long, cached: Boolean = true) {
        compositeDisposable.add(
            routineUseCase.get(categoryId, cached)
                .subscribeOn(Schedulers.io())
                .map {
                    it.mapToPresentation()
                }
                .subscribe(
                    {
                        addCategoryRoutineBinding(CategoryIdRoutineBinding(categoryId, it))
                    },
                    { e -> println(e) })
        )
        
    }

    fun setRoutine(categoryId: Long, name: String) {
        compositeDisposable.add(
            routineUseCase.set(categoryId, name)
                .subscribeOn(Schedulers.io())
                .subscribe({}, { e -> println(e) })
        )
    }

    fun getSetInfo(categoryId: Long, exerciseId: Long, cached: Boolean = true) {
        compositeDisposable.add(
            setInfoUseCase.getSetInfo(categoryId, exerciseId, cached)
                .subscribeOn(Schedulers.io())
                .map {
                    resetSetInfo()
                    it.mapToPresentation()
                }
                .subscribe({
                    it.map {
                        addSetInfo(it)
                    }
                }, { e -> println(e) })
        )
    }

    //life cycle
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}