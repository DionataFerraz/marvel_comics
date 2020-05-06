package com.dionataferraz.marvel_comics.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingBaseFragment<B : ViewDataBinding> : Fragment() {

    abstract val layoutId: Int

    abstract val loadVm: (B) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        (DataBindingUtil.inflate(inflater, layoutId, container, false) as? B)?.apply {
            lifecycleOwner = viewLifecycleOwner
            loadVm(this)
        }?.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        initializeObservables()
    }

    open fun initializeUI() {}

    open fun initializeObservables() {}

}
