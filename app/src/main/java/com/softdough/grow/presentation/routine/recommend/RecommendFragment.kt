package com.softdough.grow.presentation.routine.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.presentation.routine.RoutineFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecommendFragment : RoutineFragment<RecommendViewModel>() {

    private val vm: RecommendViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}