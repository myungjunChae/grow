package com.softdough.grow.datasource.remote

import com.google.gson.JsonObject
import com.softdough.grow.data.datasource.CategoryRemoteDataSource
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.Category
import io.reactivex.Single

class CategoryRemoteDataSourceImpl(private val api: CategoryApi) : CategoryRemoteDataSource {

    override fun get(): Single<List<Category>> {
        //TODO : 이게 왜 동작하는지 모르겠음
        return api.getAllCategory().map { it.mapToDomain() }
    }

    override fun get(type: String): Single<List<Category>> {
        return api.getTypedCategory(type).map { it.mapToDomain() }
    }

    override fun set(type: String, id: String): Single<List<Category>> {
        val `object` = JsonObject().apply{
            addProperty("categoryType", type)
            addProperty("categoryName", id)
        }
        return api.setCategory(`object`).map { it.mapToDomain() }
    }

}