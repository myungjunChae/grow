package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.softdough.grow.domain.model.Exercise

data class ExercisePojo(
    @SerializedName("routineId") @Expose val routineId: Long,
    @SerializedName("routineNmae") @Expose val routineName: String,
    @SerializedName("exerciseList") @Expose val exerciseList: List<ExerciseEntity>
)
data class ExerciseEntity(
    @SerializedName("exerciseId") @Expose val id: Long,
    @SerializedName("exerciseName") @Expose val name: String,
    @SerializedName("exercisePartials") @Expose val partials: Set<String>,
    @SerializedName("exerciseMotions") @Expose val motions: Set<String>,
    @SerializedName("exerciseTool") @Expose val tool: String
)

fun ExerciseEntity.mapToDomain(): Exercise = Exercise(id, name, partials, motions, tool)

fun List<ExerciseEntity>.mapToDomain(): List<Exercise> = map { it.mapToDomain() }