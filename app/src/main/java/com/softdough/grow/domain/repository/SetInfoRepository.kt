package com.softdough.grow.domain.repository

import com.softdough.grow.datasource.model.SetPojo
import com.softdough.grow.domain.model.SetInfo
import io.reactivex.Single

interface SetInfoRepository {
    fun setSetInfo(
        setNumber: Int,
        reps: Int,
        weight: Float,
        restTime: Int,
        routineId: Long,
        exerciseId: Long
    ): Single<SetPojo>

    fun getSetInfo(routineId: Long, exerciseId: Long, cached: Boolean): Single<List<SetInfo>>
}