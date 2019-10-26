package com.softdough.grow.datasource.remote

import com.softdough.grow.data.datasource.CategoryRemoteDataSource
import com.softdough.grow.domain.model.Category
import io.reactivex.rxjava3.core.Single

class CategoryRemoteDataSourceImpl(private val api : CategoryApi) : CategoryRemoteDataSource {
    override fun set(): Single<List<Category>> {

    }

    override fun get(): Single<List<Category>> {

    }

    override fun get(type: String): Single<List<Category>> {

    }

}