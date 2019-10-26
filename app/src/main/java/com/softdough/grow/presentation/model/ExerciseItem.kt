package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExerciseItem(
    val id: Long,
    val name: String,
    val partials: List<String>,
    val motions: List<String>,
    val tool: String
)