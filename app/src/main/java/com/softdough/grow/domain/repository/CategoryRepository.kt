package com.softdough.grow.domain.repository

import com.softdough.grow.domain.model.Category
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {

    fun get(cached: Boolean): Single<List<Category>> // 전체 카테고리
    fun get(type: String, cached: Boolean): Single<List<Category>> // 타입 한정 카테고리
}