package com.softdough.grow.data.datasource

import com.softdough.grow.datasource.model.SetPojo
import com.softdough.grow.domain.model.SetInfo
import io.reactivex.Single

interface SetInfoRemoteDataSource {
    fun setSetInfo(
        setNumber: Int,
        reps: Int,
        weight: Float,
        restTime: Int,
        routineId: Long,
        exerciseId: Long
    ) :Single<SetPojo>

    fun getSetInfo(routineId : Long, exerciseId : Long) : Single<List<SetInfo>>
}

interface SetInfoLocalDataSource {
    fun setSetInfo()

    fun getSetInfo(routineId : Long, exerciseId : Long) : Single<List<SetInfo>>
}