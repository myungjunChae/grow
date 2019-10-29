package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

interface RoutineRemoteDataSource {
    fun get(categoryId: Long): Single<List<Routine>>

    fun set(categoryId: Long, name: String): Single<List<Routine>>
}

interface RoutineLocalDataSource {
    fun get(categoryId: Long): Single<List<Routine>>

    fun set(categoryId: Long, routines: List<Routine>) : Single<List<Routine>>
}