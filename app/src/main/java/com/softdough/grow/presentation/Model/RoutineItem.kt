package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineItem(
    val id : Long,
    val name : String
)

fun List<Routine>.mapToPresentation(): List<RoutineItem>
        = map { RoutineItem(it.id, it.name) }