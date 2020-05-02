package com.dionataferraz.marvel_comics.bindingbinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun imageUrl(view: ImageView, url: String?) {
    Picasso.get().load(url).into(view)
}