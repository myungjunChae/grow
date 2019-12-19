package com.softdough.grow.presentation.ui.CreateRoutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.model.Exercise
import com.softdough.grow.domain.model.ExerciseItem
import com.softdough.grow.domain.model.RoutineItem
import com.softdough.grow.domain.model.mapToPresentation
import com.softdough.grow.domain.usecase.ExerciseUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.domain.usecase.SetInfoUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

enum class PartialExercise {
    Partial, Exercise
}

data class PartialExerciseBinding(
    var type: PartialExercise,
    val exercise: ExerciseItem? = null,
    val partial: String? = null
)

class CreateRoutineViewModel(
    private val exerciseUseCase: ExerciseUseCase,
    private val routineUseCase: RoutineUseCase,
    private val setInfoUseCase: SetInfoUseCase
) : ViewModel() {

    val exerciseList: MutableList<Long> = arrayListOf()

    val livePartialExerciseBinding = MutableLiveData<List<PartialExerciseBinding>>()

    val liveRoutine = MutableLiveData<RoutineItem>()

    val compositeDisposable = CompositeDisposable()

    fun getExerciseAll(cached: Boolean = true) {
        compositeDisposable.add(
            exerciseUseCase.getExerciseAll(cached)
                .subscribeOn(Schedulers.io())
                .map { it ->
                    {
                        val partialExerciseBinding: MutableList<PartialExerciseBinding> =
                            arrayListOf()
                        var prevPartial = ""
                        it.mapToPresentation().map {
                            it.run {
                                if (prevPartial != it.partials.first()) {
                                    prevPartial = it.partials.first()
                                    partialExerciseBinding.add(
                                        PartialExerciseBinding(
                                            PartialExercise.Partial,
                                            null,
                                            prevPartial
                                        )
                                    )
                                }

                                partialExerciseBinding.add(
                                    PartialExerciseBinding(
                                        PartialExercise.Exercise,
                                        it
                                    )
                                )
                            }
                        }
                        partialExerciseBinding
                    }()
                }
                .subscribe({ livePartialExerciseBinding.postValue(it) }, { e -> println(e) })
        )
    }

    fun setRoutine(categoryId: Long, routineName: String) {
        compositeDisposable.add(
            routineUseCase.set(categoryId, routineName)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe({
                    liveRoutine.postValue(it)
                }, { e -> println("${e}") })
        )
    }

    fun addExercise(exerciseId: Long) {
        val item = exerciseList.find { it == exerciseId }
        when (item == null) {
            false -> {
                exerciseList.remove(exerciseId)
            }
            true -> {
                exerciseList.add(exerciseId)
            }
        }
    }

    fun setSetInfo(routineId: Long, exerciseId: Long) {
        compositeDisposable.add(
            setInfoUseCase.setSetInfo(5, 12, 30f, 60, routineId, exerciseId)
                .subscribeOn(Schedulers.io())
                .subscribe({}, { e -> println(e) })
        )
    }

    fun linkRoutineExercise(routineId: Long, exerciseId: Long) {
        compositeDisposable.add(
            routineUseCase.link(routineId, exerciseId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    //setSetInfo(routineId, exerciseId)
                }, { e -> println(e) })
        )
    }
}