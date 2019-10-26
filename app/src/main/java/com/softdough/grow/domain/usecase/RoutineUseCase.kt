package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.rxjava3.core.Single

class RoutineUseCase(val routineRepository: RoutineRepository){
    fun get(categoryId : Long) : Single<List<Routine>> {
        return routineRepository.get(categoryId)
    }

    fun set(routines : List<Routine>){
    }
}