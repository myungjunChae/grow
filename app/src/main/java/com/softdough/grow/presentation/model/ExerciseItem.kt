package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExerciseItem(
    val id: Long,
    val name: String,
    val partials: Set<String>,
    val motions: Set<String>,
    val tool: String
)

fun List<Exercise>.mapToPresentation(): List<ExerciseItem> =
    map { ExerciseItem(it.id, it.name, it.partials, it.motions, it.tool) }

