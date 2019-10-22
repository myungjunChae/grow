package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineCollectionEntity(
    @SerializedName("collectionId") @Expose val id:Long,
    @SerializedName("routineName") @Expose val name:String,
    @SerializedName("collectionType") @Expose val type:String
)