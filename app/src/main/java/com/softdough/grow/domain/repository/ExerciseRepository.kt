package com.softdough.grow.domain.repository

import com.softdough.grow.domain.model.Exercise
import io.reactivex.Single

interface ExerciseRepository {
    fun getExerciseAll(cached: Boolean): Single<List<Exercise>>

    fun getExercise(routineId: Long, cached: Boolean): Single<List<Exercise>>
}

