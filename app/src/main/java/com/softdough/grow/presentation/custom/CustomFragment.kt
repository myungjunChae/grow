package com.softdough.grow.presentation.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.presentation.routine.RoutineFragment

class CustomFragment : RoutineFragment<CustomViewModel>() {

    override var viewModel: CustomViewModel = CustomViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}