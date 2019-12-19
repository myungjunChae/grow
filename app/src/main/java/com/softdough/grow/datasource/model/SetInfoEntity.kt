package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.softdough.grow.domain.model.SetInfo

data class SetPojo(
    @SerializedName("routineId") @Expose val routineId: Long,
    @SerializedName("exerciseId") @Expose val exerciseId: Long,
    @SerializedName("detailList") @Expose val detailList: List<SetInfoEntity>
)

data class SetInfoEntity(
    @SerializedName("setId") @Expose val id: Long,
    @SerializedName("setNumber") @Expose val setNumber: Int,
    @SerializedName("reps") @Expose val reps: Int,
    @SerializedName("weight") @Expose val weight: Float,
    @SerializedName("restTime") @Expose val restTime: Int
    )

fun SetInfoEntity.mapToDomain() : SetInfo = SetInfo(id, setNumber, reps, weight, restTime)

fun List<SetInfoEntity>.mapToDomain() : List<SetInfo> = map { it.mapToDomain() }