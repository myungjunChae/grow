package com.softdough.grow.domain.repository

import com.softdough.grow.domain.model.Routine
import io.reactivex.rxjava3.core.Single

interface RoutineRepository {
    fun get(categoryId: Long) : Single<List<Routine>>

    fun set(routines : List<Routine>)
}