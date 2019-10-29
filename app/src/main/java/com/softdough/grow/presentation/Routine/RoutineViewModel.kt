package com.softdough.grow.presentation.Routine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.model.CategoryItem
import com.softdough.grow.domain.model.RoutineItem
import com.softdough.grow.domain.model.mapToPresentation
import com.softdough.grow.domain.usecase.*
import com.softdough.grow.presentation.Resource.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class RoutineViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase
) : ViewModel() {

    val categories = MutableLiveData<List<CategoryItem>>()
    val routines = MutableLiveData<List<RoutineItem>>()
    val compositeDisposable = CompositeDisposable()

    fun getCategory(type: String, cached: Boolean = true) {
        compositeDisposable.add(
            categoryUseCase.get(type, cached)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe({ categories.postValue(it) }, { e -> println(e) })
        )
    }

    fun setCategory(type: String, name: String) {
        compositeDisposable.add(
            categoryUseCase.set(type, name)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe({ categories.postValue(it) }, { e -> println(e) })
        )
    }

    fun getRoutine(categoryId: Long, cached: Boolean = true) {
        compositeDisposable.add(
            routineUseCase.get(categoryId, cached)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe({ routines.postValue(it) }, { e -> println(e) })
        )
    }

    fun setRoutine(categoryId : Long, name : String){
        compositeDisposable.add(
            routineUseCase.set(categoryId, name)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe({ routines.postValue(it) }, { e -> println(e) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}