package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountItem(
    val email: String?,
    val name: String,
    val weight: Float,
    val height: Float,
    val gender: String,
    val age: Int,
    val accountId: Long?
)