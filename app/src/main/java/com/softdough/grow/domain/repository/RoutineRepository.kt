package com.softdough.grow.domain.repository

import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

interface RoutineRepository {

    fun get(categoryId: Long, cached: Boolean): Single<List<Routine>>

    fun set(categoryId : Long, routineName : String) : Single<List<Routine>>
}