package com.softdough.grow.datasource.remote

import com.google.gson.JsonObject
import com.softdough.grow.data.datasource.SetInfoRemoteDataSource
import com.softdough.grow.datasource.model.SetPojo
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.SetInfo
import io.reactivex.Single

class SetInfoRemoteDataSourceImpl(private val api: SetInfoApi) : SetInfoRemoteDataSource {
    override fun setSetInfo(
        setNumber: Int,
        reps: Int,
        weight: Float,
        restTime: Int,
        routineId: Long,
        exerciseId: Long
    )  :Single<SetPojo>{
        var `object` = JsonObject().apply {
            addProperty("setNumber", setNumber)
            addProperty("reps", reps)
            addProperty("weight", weight)
            addProperty("restTime", restTime)
            addProperty("routineId", routineId)
            addProperty("exerciseId", exerciseId)
        }

        return api.setSetInfo(`object`)
    }

    override fun getSetInfo(routineId: Long, exerciseId: Long): Single<List<SetInfo>> {
        return api.getSetInfo(routineId, exerciseId)
            .map { pojo ->
                pojo.detailList.mapToDomain()
            }
    }
}