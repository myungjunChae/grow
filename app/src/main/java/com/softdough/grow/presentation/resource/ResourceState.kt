package com.softdough.grow.presentation.resource

sealed class ResourceState(){
    object LOADING: ResourceState()
    object SUCCESS: ResourceState()
    object ERROR: ResourceState()
}