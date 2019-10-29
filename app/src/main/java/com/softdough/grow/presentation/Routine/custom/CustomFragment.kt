package com.softdough.grow.presentation.Routine.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.injectionFeature
import com.softdough.grow.presentation.Routine.RoutineFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class CustomFragment : RoutineFragment<CustomViewModel>() {

    private val vm: CustomViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        startKoin { injectionFeature() }



        return binding.root
    }
}