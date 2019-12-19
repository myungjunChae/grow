package com.softdough.grow.data.repository

import com.softdough.grow.datasource.local.SetInfoLocalDataSourceImpl
import com.softdough.grow.datasource.model.SetPojo
import com.softdough.grow.datasource.remote.SetInfoRemoteDataSourceImpl
import com.softdough.grow.domain.model.SetInfo
import com.softdough.grow.domain.repository.SetInfoRepository
import io.reactivex.Single

class SetInfoRepositoryImpl(
    private val remoteDataSource: SetInfoRemoteDataSourceImpl,
    private val localDataSource: SetInfoLocalDataSourceImpl
) : SetInfoRepository {

    override fun setSetInfo(
        setNumber: Int,
        reps: Int,
        weight: Float,
        restTime: Int,
        routineId: Long,
        exerciseId: Long
    ): Single<SetPojo> {
        return remoteDataSource.setSetInfo(setNumber, reps, weight, restTime, routineId, exerciseId)
    }

    override fun getSetInfo(
        routineId: Long,
        exerciseId: Long,
        cached: Boolean
    ): Single<List<SetInfo>> {
        return when (cached) {
            false -> {
                remoteDataSource.getSetInfo(routineId, exerciseId)
            }
            true -> {
                localDataSource.getSetInfo(routineId, exerciseId)
            }
        }
    }
}