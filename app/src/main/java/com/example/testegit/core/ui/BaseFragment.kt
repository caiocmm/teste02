package com.example.testegit.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun layoutId(): Int
    abstract fun init()
    open fun create() {}
    open fun destroy() {}

    var binding: Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutId(),
            container,
            false
        )
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun pop(): Boolean {
        return findNavController().popBackStack()
    }

    fun showLoading(show: Boolean) {
        (activity as MainActivity).showLoading(show)
    }

    override fun onDestroy() {
        destroy()
        super.onDestroy()
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}