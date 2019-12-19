package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Exercise
import io.reactivex.Single

interface ExerciseLocalDataSource {
    fun setExerciseAll(): Single<List<Exercise>>

    fun getExerciseAll(): Single<List<Exercise>>

    fun getExercise(rouitneId: Long): Single<List<Exercise>>
}

interface ExerciseRemoteDataSource {
    fun getExerciseAll(): Single<List<Exercise>>

    fun getExercise(routineId: Long): Single<List<Exercise>>
}