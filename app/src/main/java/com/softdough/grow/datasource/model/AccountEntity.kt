package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountEntity(
    @SerializedName("userEmail") @Expose val email: String,
    @SerializedName("userName") @Expose val name: String,
    @SerializedName("weight") @Expose val weight: Float,
    @SerializedName("height") @Expose val height: Float,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("birth") @Expose val birth: String,
    @SerializedName("kakaoId") @Expose val kakaoId: Long

    //@SerializedName("routineCollectionList") @Expose val routineCollectionList: List<RoutineCollectionEntity>
    //@SerializedName("routineSetList") @Expose val routineSetList: List<RoutineSet>


)