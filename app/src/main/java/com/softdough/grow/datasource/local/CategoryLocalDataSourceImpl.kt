package com.softdough.grow.datasource.local

import com.softdough.grow.data.datasource.CategoryLocalDataSource
import com.softdough.grow.domain.model.Category
import io.reactivex.rxjava3.core.Single

class CategoryLocalDataSourceImpl(private val sharedPreference: SharedPreference) : CategoryLocalDataSource {
    override fun set(type: String, list: List<Category>): Single<List<Category>> {

    }

    override fun get(): Single<List<Category>> {

    }

    override fun get(type: String): Single<List<Category>> {

    }
}