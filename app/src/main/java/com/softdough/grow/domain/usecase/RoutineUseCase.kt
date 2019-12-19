package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.Single

class RoutineUseCase(val routineRepository: RoutineRepository) {
    fun get(categoryId: Long, cached: Boolean = true): Single<List<Routine>> =
        routineRepository.get(categoryId, cached)


    fun set(categoryId: Long, routineName: String): Single<Routine> {
        return routineRepository.set(categoryId, routineName)
    }

    fun link(routineId: Long, exerciseId: Long): Single<Routine> =
        routineRepository.link(routineId, exerciseId)

}