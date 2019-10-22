package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineSetEntity(
    @SerializedName("setId") @Expose val id: Long,
    @SerializedName("setNumber") @Expose val setNumber: Int,
    @SerializedName("reps") @Expose val reps: Int,
    @SerializedName("weight") @Expose val weight: Float,
    @SerializedName("restTime") @Expose val restTime: Int
    )