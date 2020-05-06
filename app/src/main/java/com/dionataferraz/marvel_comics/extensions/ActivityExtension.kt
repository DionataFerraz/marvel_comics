package com.dionataferraz.marvel_comics.extensions

import android.app.Activity
import android.transition.Fade
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.transitions.DetailsTransition
import com.dionataferraz.marvel_comics.ui.adapters.CharactersAdapter

fun Activity.hideKeyboard(view: View?) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.showFragment(fragment: Fragment, tag: String, view: CharactersAdapter.ViewHolder) {
    activity?.run {

        fragment.sharedElementEnterTransition = DetailsTransition()
        fragment.enterTransition = Fade()
        exitTransition = Fade()
        fragment.sharedElementReturnTransition = DetailsTransition()

        Log.e("showFragment", "${view.binding.ivCharacter.transitionName}")

        supportFragmentManager.let { fragManager ->
            fragManager.findFragmentByTag(fragManager.fragments.first().tag)?.run {
                fragManager.beginTransaction()
                    .hide(this)
                    .addSharedElement(view.binding.ivCharacter, "transitionCharacter")
                    .add(R.id.container_fragment, fragment, tag)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
