package com.softdough.grow.data.repository

import com.softdough.grow.datasource.local.ExerciseLocalDataSourceImpl
import com.softdough.grow.datasource.remote.ExerciseRemoteDataSourceImpl
import com.softdough.grow.domain.model.Exercise
import com.softdough.grow.domain.repository.ExerciseRepository
import io.reactivex.Single

class ExerciseRepositoryImpl(
    private val remoteDataSource: ExerciseRemoteDataSourceImpl,
    private val localDataSource: ExerciseLocalDataSourceImpl
) : ExerciseRepository {

    override fun getExerciseAll(cached: Boolean): Single<List<Exercise>> =
        when (cached) {
            false -> remoteDataSource.getExerciseAll()
            true -> localDataSource.getExerciseAll()
        }

    override fun getExercise(routineId: Long, cahced: Boolean): Single<List<Exercise>> =
        when (cahced) {
            false -> remoteDataSource.getExercise(routineId)
            true -> localDataSource.getExercise(routineId)
        }

}