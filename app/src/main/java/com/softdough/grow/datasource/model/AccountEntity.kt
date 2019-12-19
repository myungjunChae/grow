package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.softdough.grow.domain.model.Account

data class AccountEntity(
    @SerializedName("userEmail") @Expose val email: String,
    @SerializedName("userName") @Expose val name: String,
    @SerializedName("weight") @Expose val weight: Float,
    @SerializedName("height") @Expose val height: Float,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("age") @Expose val age: Int,
    @SerializedName("accountId") @Expose val accountId: Long
)

fun AccountEntity.mapToDomain() : Account = Account(email, name, weight, height, gender, age, accountId )

