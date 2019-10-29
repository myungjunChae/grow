package com.softdough.grow.datasource.remote

import com.softdough.grow.data.datasource.RoutineRemoteDataSource
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

class RoutineRemoteDataSourceImpl(private val api: RoutineApi) : RoutineRemoteDataSource {
    override fun get(categoryId: Long): Single<List<Routine>> {
        return api.getRoutine(categoryId).map { it.mapToDomain() }
    }

    override fun set(categoryId: Long, name : String): Single<List<Routine>> {
        return api.setRoutine(categoryId, name).map { it.mapToDomain() }
    }

}