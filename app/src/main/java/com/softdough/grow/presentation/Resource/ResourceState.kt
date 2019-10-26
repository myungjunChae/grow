package com.softdough.grow.presentation.Resource

sealed class ResourceState(){
    object LOADING: ResourceState()
    object SUCCESS: ResourceState()
    object ERROR: ResourceState()
}