package com.dionataferraz.marvel_comics.ui

import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.base.BindingBaseActivity
import com.dionataferraz.marvel_comics.databinding.ActivityCharacterBinding
import com.dionataferraz.presentation.CharacterViewModel
import org.koin.android.ext.android.inject

class CharacterActivity : BindingBaseActivity<ActivityCharacterBinding>() {

    private val viewModel: CharacterViewModel by inject()

    override val layoutId: Int = R.layout.activity_character

    override val loadVm: (ActivityCharacterBinding) -> Unit = { binding ->
        binding.vm = viewModel
    }

}
