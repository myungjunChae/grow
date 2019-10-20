package com.softdough.grow.presentation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutineCollectionItems (
    val list: List<RoutineCollectionItem>
)

data class RoutineCollectionItem (
    val resourceId: Int,
    val title: String
)
