package com.softdough.grow.domain.model

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineItem(
    val id: Long,
    val name: String,
    val url: String,
    var image: Bitmap?
)

fun List<Routine>.mapToPresentation(): List<RoutineItem> = map { it.mapToPresentation() }

fun Routine.mapToPresentation(): RoutineItem = RoutineItem(id, name, url, null)