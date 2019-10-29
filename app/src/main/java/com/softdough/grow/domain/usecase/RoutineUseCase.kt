package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.Single

class RoutineUseCase(val routineRepository: RoutineRepository){
    fun get(categoryId : Long, cached: Boolean = true) : Single<List<Routine>> =
        routineRepository.get(categoryId, cached)


    fun set(categoryId : Long, routineName : String) : Single<List<Routine>> =
        routineRepository.set(categoryId, routineName)

}