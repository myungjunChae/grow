package com.softdough.grow.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.softdough.grow.domain.model.Category

data class CategoryEntity(
    @SerializedName("categoryId") @Expose val id: Long,
    @SerializedName("categoryType") @Expose val type: String,
    @SerializedName("categoryName") @Expose val name: String,
    @SerializedName("imageUrl") @Expose val url: String
)

fun CategoryEntity.mapToDomain() : Category = Category(id, name, type, url)

fun List<CategoryEntity>.mapToDomain(): List<Category> = map { it.mapToDomain() }