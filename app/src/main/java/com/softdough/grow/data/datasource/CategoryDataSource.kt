package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Category
import io.reactivex.rxjava3.core.Single

interface CategoryLocalDataSource {
    fun set(type: String, list : List<Category>) : Single<List<Category>>

    fun get() : Single<List<Category>>

    fun get(type: String) : Single<List<Category>>
}

interface CategoryRemoteDataSource {
    fun set() : Single<List<Category>>

    fun get() : Single<List<Category>>

    fun get(type: String) : Single<List<Category>>
}