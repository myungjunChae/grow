package com.softdough.grow.domain.usecase

import com.softdough.grow.data.repository.ExerciseRepositoryImpl
import com.softdough.grow.domain.model.Exercise
import io.reactivex.Single

class ExerciseUseCase(private val exerciseRepository : ExerciseRepositoryImpl){
    fun getExerciseAll(cached : Boolean) : Single<List<Exercise>> =
        exerciseRepository.getExerciseAll(cached)

    fun getExercise(routineId : Long, cached : Boolean) : Single<List<Exercise>> =
        exerciseRepository.getExercise(routineId, cached)
}