package com.softdough.grow.datasource.remote

import com.softdough.grow.data.datasource.RoutineRemoteDataSource
import com.softdough.grow.domain.model.Routine
import io.reactivex.rxjava3.core.Single

class RoutineRemoteDataSourceImpl(private val api : RoutineApi) : RoutineRemoteDataSource{
    override fun get(categoryId: Long): Single<List<Routine>> {

    }

    override fun set(routines: List<Routine>): Single<List<Routine>> {

    }

}