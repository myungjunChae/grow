package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.repository.CategoryRepository
import io.reactivex.Single

class CategoryUseCase(val categoryRepository: CategoryRepository) {

    fun get(cached: Boolean): Single<List<Category>> {
        return categoryRepository.get(cached)
    }

    fun get(type: String, cached: Boolean): Single<List<Category>> {
        return categoryRepository.get(type, cached)
    }

    fun set(type: String, name: String): Single<List<Category>> {
        return categoryRepository.set(type, name)
    }
}