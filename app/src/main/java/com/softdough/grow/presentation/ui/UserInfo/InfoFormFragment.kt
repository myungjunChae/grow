package com.softdough.grow.presentation.ui.UserInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softdough.grow.R
import com.softdough.grow.databinding.FragmentInfoFormBinding
import com.softdough.grow.presentation.base.BaseFragment

class InfoFormFragment : BaseFragment<FragmentInfoFormBinding>() {
    override val resourceId: Int = R.layout.fragment_info_form

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}