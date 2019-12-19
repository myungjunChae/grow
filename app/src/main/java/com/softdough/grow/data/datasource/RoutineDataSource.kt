package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

interface RoutineRemoteDataSource {
    fun get(categoryId: Long): Single<List<Routine>>

    fun set(categoryId: Long, name: String): Single<Routine>

    fun link(routineId: Long, exerciseId: Long): Single<Routine>
}

interface RoutineLocalDataSource {
    fun get(categoryId: Long): Single<List<Routine>>

    fun set(categoryId: Long, routines: List<Routine>): Single<List<Routine>>
}