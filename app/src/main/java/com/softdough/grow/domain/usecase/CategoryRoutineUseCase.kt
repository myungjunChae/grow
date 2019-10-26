package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.model.Routine
import com.softdough.grow.domain.repository.CategoryRepository
import com.softdough.grow.domain.repository.RoutineRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

data class CategoryRoutinesBinding(
    val category: Category,
    val routines: List<Routine>
)

class CategoryRoutinesUseCase(
    private val categoryRepository: CategoryRepository,
    private val routineRepository: RoutineRepository
) {
    fun get(): Single<List<CategoryRoutinesBinding>> {
        val _list = mutableListOf<CategoryRoutinesBinding>()

        categoryRepository.get()
            .map { categories ->
                categories.forEach { category ->
                    routineRepository.get(category.id)
                        .map { routines ->
                            _list.add(bind(category, routines))
                        }
                }
            }
        return Single.just(_list)
    }
}

fun bind(category: Category, routines: List<Routine>) = CategoryRoutinesBinding(category, routines)
















