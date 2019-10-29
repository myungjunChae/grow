package com.softdough.grow.presentation.Routine.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.presentation.Routine.RoutineFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecommendFragment : RoutineFragment<RecommendViewModel>() {

    private val vm: RecommendViewModel by viewModel()
    //TODO enum으로 바꾸기
    private val CATEGROY_TYPE = "recommend"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.getCategory(CATEGROY_TYPE)

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}