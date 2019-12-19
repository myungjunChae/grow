package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SetInfo(
    val id: Long,
    val setNumber: Int,
    val reps: Int,
    val weight: Float,
    val restTime: Int
)