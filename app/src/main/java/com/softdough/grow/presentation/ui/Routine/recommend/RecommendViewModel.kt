package com.softdough.grow.presentation.ui.Routine.recommend

import com.softdough.grow.domain.model.SetInfo
import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.domain.usecase.SetInfoUseCase
import com.softdough.grow.presentation.ui.Routine.RoutineViewModel

class RecommendViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val routineUseCase: RoutineUseCase,
    private val setInfoUseCase: SetInfoUseCase
) : RoutineViewModel(categoryUseCase, routineUseCase, setInfoUseCase) {

}