package com.dionataferraz.marvel_comics.extensions

import android.view.View
import com.dionataferraz.marvel_comics.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).run {
        setAction(R.string.snackbar_ok) {
            dismiss()
        }
    }.show()
}