package com.softdough.grow.data.repository

import com.softdough.grow.datasource.local.RoutineLocalDataSourceImpl
import com.softdough.grow.datasource.remote.RoutineRemoteDataSourceImpl
import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.Single

class RoutineRepositoryImpl(
    private val remoteDataSource: RoutineRemoteDataSourceImpl,
    private val localDataSource: RoutineLocalDataSourceImpl
) : RoutineRepository {
    override fun get(categoryId: Long, cached: Boolean): Single<List<Routine>> =
        when (cached) {
            false -> remoteDataSource.get(categoryId)
            true -> localDataSource.get(categoryId)
        }

    override fun set(categoryId: Long, name: String): Single<Routine> =
        remoteDataSource.set(categoryId, name)

    override fun link(routineId: Long, exerciseId: Long): Single<Routine> =
        remoteDataSource.link(routineId, exerciseId)

}