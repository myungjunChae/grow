package com.softdough.grow.presentation.resource

data class Resource<out T>(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)
