package com.softdough.grow.presentation.ui.Routine.custom

import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.domain.usecase.SetInfoUseCase
import com.softdough.grow.presentation.ui.Routine.RoutineViewModel

class CustomViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase,
    private val setInfoUseCase: SetInfoUseCase
) : RoutineViewModel(categoryUseCase, routineUseCase, setInfoUseCase) {

}