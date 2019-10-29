package com.softdough.grow.presentation.Routine.custom

import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.presentation.Routine.RoutineViewModel

class CustomViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase
) : RoutineViewModel(categoryUseCase, routineUseCase) {

}