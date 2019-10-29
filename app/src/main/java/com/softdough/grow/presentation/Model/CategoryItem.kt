package com.softdough.grow.domain.model

import androidx.lifecycle.Transformations.map
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryItem(
    val id:Long,
    val name:String,
    val type:String
)

fun List<Category>.mapToPresentation(): List<CategoryItem>
    = map { CategoryItem(it.id, it.name, it.type) }