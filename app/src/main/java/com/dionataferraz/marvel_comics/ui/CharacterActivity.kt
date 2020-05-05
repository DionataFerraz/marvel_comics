package com.dionataferraz.marvel_comics.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.base.BindingBaseActivity
import com.dionataferraz.marvel_comics.databinding.ActivityCharacterBinding
import com.dionataferraz.marvel_comics.extensions.hideKeyboard
import com.dionataferraz.presentation.CharacterViewModel
import kotlinx.android.synthetic.main.activity_character.*
import org.koin.android.ext.android.inject
import com.dionataferraz.marvel_comics.extensions.showSnackbar
import com.dionataferraz.marvel_comics.ui.adapters.ComicsAdapter

class CharacterActivity : BindingBaseActivity<ActivityCharacterBinding>() {

    private val viewModel: CharacterViewModel by inject()

    override val layoutId: Int = R.layout.activity_character

    override val loadVm: (ActivityCharacterBinding) -> Unit = { binding ->
        binding.vm = viewModel
    }

    override fun initializeUI() {
        rv_comics.adapter = ComicsAdapter()
        rv_series.adapter = ComicsAdapter()

        actv_character_name.run {
            setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.loadCharacter(view.text.toString())
                }
                false
            }

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    viewModel.onTextChange(editable?.toString())
                }

                override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    override fun initializeViewModels() {
        viewModel.run {
            closeKeyboard().observe(this@CharacterActivity, Observer { shouldCloseKeyboard ->
                if (shouldCloseKeyboard) {
                    cl_container.hideKeyboard()
                }
            })

            error.observe(this@CharacterActivity, Observer { message -> cl_container.showSnackbar(message) })
        }
    }
}
