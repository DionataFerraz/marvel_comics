package com.dionataferraz.marvel_comics.binding

import android.text.Editable
import android.text.Html.fromHtml
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:onClickActionSearch")
fun onClickActionSearch(textView: TextView?, onClick: (text: String) -> Unit) {
    textView?.setOnEditorActionListener { view, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onClick(view.text.toString())
        }
        false
    }
}

@BindingAdapter("app:afterTextChanged")
fun afterTextChanged(textView: TextView?, onTextChanged: (text: String?) -> Unit) {
    textView?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            onTextChanged(editable?.toString())
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

@BindingAdapter("app:textHTML")
fun textHTML(textView: TextView, text: String?) {
    textView.text = fromHtml(text ?: "")
}