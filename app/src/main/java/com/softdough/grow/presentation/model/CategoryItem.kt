package com.softdough.grow.domain.model

import android.graphics.Bitmap
import androidx.lifecycle.Transformations.map
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryItem(
    val id:Long,
    val name:String,
    val type:String,
    val url:String,
    var image: Bitmap?
)

fun List<Category>.mapToPresentation(): List<CategoryItem>
    = map { CategoryItem(it.id, it.name, it.type, it.url, null) }