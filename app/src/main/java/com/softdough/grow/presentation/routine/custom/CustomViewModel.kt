package com.softdough.grow.presentation.routine.custom

import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.presentation.routine.RoutineViewModel

class CustomViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase
) : RoutineViewModel(categoryUseCase, routineUseCase) {

}