package com.softdough.grow.domain.usecase

import com.softdough.grow.data.repository.SetInfoRepositoryImpl
import com.softdough.grow.datasource.model.SetPojo
import com.softdough.grow.domain.model.SetInfo
import io.reactivex.Single

class SetInfoUseCase(private val repository: SetInfoRepositoryImpl) {
    fun setSetInfo(
        setNumber: Int,
        reps: Int,
        weight: Float,
        restTime: Int,
        routineId: Long,
        exerciseId: Long
    ): Single<SetPojo> {
        return repository.setSetInfo(setNumber, reps, weight, restTime, routineId, exerciseId)
    }

    fun getSetInfo(
        routineId: Long, exerciseId: Long, cached: Boolean
    ): Single<List<SetInfo>> {
        return repository.getSetInfo(routineId, exerciseId, cached)
    }
}