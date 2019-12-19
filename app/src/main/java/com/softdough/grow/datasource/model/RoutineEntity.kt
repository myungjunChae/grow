package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.softdough.grow.domain.model.Routine

data class RoutineEntity(
    @SerializedName("routineId") @Expose val id : Long,
    @SerializedName("routineName") @Expose val name : String,
    @SerializedName("imageUrl") @Expose val url: String
)

fun RoutineEntity.mapToDomain() = Routine(id, name, url)

fun List<RoutineEntity>.mapToDomain() = map { it.mapToDomain() }