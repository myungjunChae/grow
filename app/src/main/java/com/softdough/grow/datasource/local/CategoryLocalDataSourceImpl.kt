package com.softdough.grow.datasource.local

import com.google.gson.Gson
import com.softdough.grow.data.datasource.CategoryLocalDataSource
import com.softdough.grow.domain.model.Category
import com.google.gson.reflect.TypeToken
import io.reactivex.Single


class CategoryLocalDataSourceImpl(private val pref: SharedPreference) : CategoryLocalDataSource {

    override fun get(): Single<List<Category>> {
        //TODO : 로컬 데이터소스에서 전체 검색 방법 모색
        return Single.just(null)
    }

    override fun get(type: String): Single<List<Category>> {
        val classType = object : TypeToken<List<Category>>() {}.type
        return Single.just(pref.getValue(type, classType) as List<Category>)
    }

    override fun set(type: String, list: List<Category>): Single<List<Category>> {
        return Single.just(pref.setValue(type, list))
    }

}