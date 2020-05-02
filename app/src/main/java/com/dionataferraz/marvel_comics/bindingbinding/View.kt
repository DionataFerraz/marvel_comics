package com.dionataferraz.marvel_comics.bindingbinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun visible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}