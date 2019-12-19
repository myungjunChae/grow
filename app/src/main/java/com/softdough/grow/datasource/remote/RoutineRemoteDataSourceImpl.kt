package com.softdough.grow.datasource.remote

import com.google.gson.JsonObject
import com.softdough.grow.data.datasource.RoutineRemoteDataSource
import com.softdough.grow.datasource.model.RoutineEntity
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.Account
import com.softdough.grow.domain.model.Routine
import io.reactivex.Single

class RoutineRemoteDataSourceImpl(private val api: RoutineApi) : RoutineRemoteDataSource {
    override fun get(categoryId: Long): Single<List<Routine>> {
        return api.getRoutine(categoryId).map { it.mapToDomain() }
    }

    override fun set(categoryId: Long, name: String): Single<Routine> {
        val `object` = JsonObject().apply {
            addProperty("categoryId", categoryId)
            addProperty("routineName", name)
        }

        return api.setRoutine(`object`).map { it.mapToDomain() }
    }

    override fun link(routineId: Long, exerciseId: Long): Single<Routine> {
        var `object` = JsonObject().apply {
            addProperty("routineId", routineId)
            addProperty("exerciseId", exerciseId)
        }

        return api.linkRoutineExercise(`object`).map { it.mapToDomain() }
    }
}