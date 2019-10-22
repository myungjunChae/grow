package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExerciseEntity(
    @SerializedName("exerciseId") @Expose val id: Long,
    @SerializedName("exerciseName") @Expose val name: String,
    @SerializedName("exercisePartials") @Expose val partials: List<String>,
    @SerializedName("exerciseMotions") @Expose val motions: List<String>,
    @SerializedName("exerciseTool") @Expose val tool: String
)