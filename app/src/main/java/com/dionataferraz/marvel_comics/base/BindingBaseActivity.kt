package com.dionataferraz.marvel_comics.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingBaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int

    abstract val loadVm: (B) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (DataBindingUtil.setContentView(this, layoutId) as? B)?.apply {
            lifecycleOwner = this@BindingBaseActivity
            loadVm(this)
        }

        initializeUI()
        initializeViewModels()
    }

    open fun initializeUI() {}

    open fun initializeViewModels() {}

}
