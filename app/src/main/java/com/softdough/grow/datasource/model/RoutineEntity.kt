package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineEntity(
    @SerializedName("routineId") @Expose val id : Long,
    @SerializedName("routineName") @Expose val name : String
)