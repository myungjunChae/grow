package com.softdough.grow.presentation.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softdough.grow.R

class CustomFragment : Fragment() {

    private lateinit var customViewModel: CustomViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customViewModel =
            ViewModelProviders.of(this).get(CustomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_custom, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        customViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}