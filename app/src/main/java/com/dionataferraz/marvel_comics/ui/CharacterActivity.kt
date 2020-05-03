package com.dionataferraz.marvel_comics.ui

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

    private val adapter by lazy {
        ComicsAdapter().apply { rv_comics.adapter = this }
    }

    override fun initializeViewModels() {
        viewModel.run {
            closeKeyboard().observe(this@CharacterActivity, Observer { shouldCloseKeyboard ->
                if (shouldCloseKeyboard) {
                    cl_container.hideKeyboard()
                }
            })

            comicPresentation().observe(this@CharacterActivity, Observer { comics ->
                adapter.submitList(comics)
            })

            error.observe(
                this@CharacterActivity,
                Observer { message -> cl_container.showSnackbar(message) })
        }
    }
}
