package com.softdough.grow.datasource.remote

import com.softdough.grow.data.datasource.ExerciseRemoteDataSource
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.Exercise
import io.reactivex.Single

class ExerciseRemoteDataSourceImpl(private val api: ExerciseApi) : ExerciseRemoteDataSource{
    override fun getExercise(routineId: Long): Single<List<Exercise>> {
        return api.getExercise(routineId).map{ it.exerciseList.mapToDomain() }
    }

    override fun getExerciseAll() : Single<List<Exercise>> {
        return api.getExerciseAll().map { it.mapToDomain() }
    }
}