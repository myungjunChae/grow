package com.softdough.grow.presentation.Routine.recommend

import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.presentation.Routine.RoutineViewModel

class RecommendViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase
) : RoutineViewModel(categoryUseCase, routineUseCase) {

}