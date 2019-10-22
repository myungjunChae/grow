package com.softdough.grow.presentation.routine.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.presentation.routine.RoutineFragment

class RecommendFragment : RoutineFragment<RecommendViewModel>() {

    override var viewModel: RecommendViewModel = RecommendViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}