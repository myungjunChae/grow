package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SetInfoItem(
    val id: Long,
    val setNumber: Int,
    val reps: Int,
    val weight: Float,
    val restTime: Int
)

fun List<SetInfo>.mapToPresentation(): List<SetInfoItem> =
    map { SetInfoItem(it.id, it.setNumber, it.reps, it.weight, it.restTime) }