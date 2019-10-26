package com.softdough.grow.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    val id:Long,
    val name:String,
    val type:String
)