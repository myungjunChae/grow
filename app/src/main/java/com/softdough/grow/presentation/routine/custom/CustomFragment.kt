package com.softdough.grow.presentation.routine.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.domain.model.Routine
import com.softdough.grow.presentation.routine.RoutineViewModel
import com.softdough.grow.presentation.routine.RoutineFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomFragment : RoutineFragment<CustomViewModel>() {

    private val vm: CustomViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}