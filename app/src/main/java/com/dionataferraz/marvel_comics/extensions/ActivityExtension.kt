package com.dionataferraz.marvel_comics.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.dionataferraz.marvel_comics.R

fun Activity.hideKeyboard(view: View?) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.showFragment(fragment: Fragment, tag: String) {
    activity?.run {
        supportFragmentManager.let { fragManager ->
            fragManager.findFragmentByTag(fragManager.fragments.first().tag)?.run {
                fragManager.beginTransaction()
                    .hide(this)
                    .add(R.id.container_fragment, fragment, tag)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
        }
    }
}
