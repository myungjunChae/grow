package com.softdough.grow.presentation.ui.Workout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.model.ExerciseItem
import com.softdough.grow.domain.model.mapToPresentation
import com.softdough.grow.domain.usecase.ExerciseUseCase
import com.softdough.grow.domain.usecase.SetInfoUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WorkoutViewModel(
    private val exerciseUseCase: ExerciseUseCase,
    private val setInfoUseCase: SetInfoUseCase
) : ViewModel() {

    val liveExercises = MutableLiveData<List<ExerciseItem>>()
    var exercises: MutableList<ExerciseItem> = arrayListOf()

    val compositeDisposable = CompositeDisposable()

    fun resetExercises() {
        exercises = arrayListOf()
    }

    fun addExercise(item: ExerciseItem) {
        exercises.add(item)
        liveExercises.postValue(exercises)
    }

    fun getExercise(routineId: Long, cached: Boolean = true) {
        compositeDisposable.add(
            exerciseUseCase.getExercise(routineId, cached)
                .subscribeOn(Schedulers.io())
                .map {
                    resetExercises()
                    it.mapToPresentation()
                }
                .subscribe({
                    it.map {
                        addExercise(it)
                    }
                }, { e -> println(e) })
        )
    }
}