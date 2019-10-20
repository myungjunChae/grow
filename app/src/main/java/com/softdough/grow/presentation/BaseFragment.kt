package com.softdough.grow.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.softdough.grow.presentation.recommend.RecommendViewModel

abstract class BaseFragment<T_DATA_BINDING : ViewDataBinding, T_VIEW_MODEL : ViewModel> :
    Fragment() {

    abstract val resourceId: Int

    abstract var viewModel: T_VIEW_MODEL

    lateinit var binding: T_DATA_BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(viewModel::class.java)

        binding = DataBindingUtil.inflate<T_DATA_BINDING>(inflater, resourceId, container, false)

        return null
    }
}