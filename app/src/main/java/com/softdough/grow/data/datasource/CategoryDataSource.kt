package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Category
import io.reactivex.Single

interface CategoryLocalDataSource {
    fun get(): Single<List<Category>>

    fun get(type: String): Single<List<Category>>

    fun set(type: String, list: List<Category>): Single<List<Category>>
}

interface CategoryRemoteDataSource {
    fun get(): Single<List<Category>>

    fun get(type: String): Single<List<Category>>

    fun set(type: String, name: String): Single<List<Category>>
}