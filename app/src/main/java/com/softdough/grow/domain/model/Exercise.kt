package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Exercise(
    val id: Long,
    val name: String,
    val partials: Set<String>,
    val motions: Set<String>,
    val tool: String
)

