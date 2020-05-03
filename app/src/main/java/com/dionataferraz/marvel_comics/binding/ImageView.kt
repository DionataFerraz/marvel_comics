package com.dionataferraz.marvel_comics.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dionataferraz.marvel_comics.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun imageUrl(view: ImageView, url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.img_loading).into(view)
}