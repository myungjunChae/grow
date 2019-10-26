package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Routine
import io.reactivex.rxjava3.core.Single

interface RoutineRemoteDataSource{
    fun get(categoryId: Long) : Single<List<Routine>>

    fun set(routines : List<Routine>) : Single<List<Routine>>
}

interface RoutineLocalDataSource{
    fun get(categoryId: Long) : Single<List<Routine>>

    fun set(routines : List<Routine>)
}