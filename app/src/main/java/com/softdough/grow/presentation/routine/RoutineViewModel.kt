package com.softdough.grow.presentation.routine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.model.CategoryItem
import com.softdough.grow.domain.model.RoutineItem
import com.softdough.grow.domain.usecase.*
import com.softdough.grow.presentation.Resource.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class RoutineViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase
) : ViewModel() {

    val categories = MutableLiveData<Resource<List<CategoryItem>>>()
    val routines = MutableLiveData<Resource<List<RoutineItem>>>()
    val compositeDisposable = CompositeDisposable()

    fun getCategory(){

    }

    fun getRoutine(){

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}