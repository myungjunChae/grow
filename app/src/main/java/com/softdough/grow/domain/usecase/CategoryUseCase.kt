package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single

class CategoryUseCase(val categoryRepository: CategoryRepository) {

    fun get(type : String) : Single<List<Category>> {
        return categoryRepository.get(type)
    }
}