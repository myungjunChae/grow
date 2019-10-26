package com.softdough.grow.data.repository

import com.softdough.grow.datasource.local.RoutineLocalDataSourceImpl
import com.softdough.grow.datasource.remote.RoutineRemoteDataSourceImpl
import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.rxjava3.core.Single

class RoutineRepositoryImpl(
    private val remoteDataSource: RoutineRemoteDataSourceImpl,
    private val localDataSource: RoutineLocalDataSourceImpl
) : RoutineRepository {
    override fun get(categoryId: Long): Single<List<Routine>> {
    }

    override fun set(routines: List<Routine>) {
    }

}