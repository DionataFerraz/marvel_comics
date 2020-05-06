package com.dionataferraz.marvel_comics.ui.fragments

import androidx.lifecycle.Observer
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.base.BindingBaseFragment
import com.dionataferraz.marvel_comics.databinding.FragmentCharactersBinding
import com.dionataferraz.marvel_comics.extensions.hideKeyboard
import com.dionataferraz.marvel_comics.extensions.showFragment
import com.dionataferraz.marvel_comics.extensions.showSnackbar
import com.dionataferraz.marvel_comics.ui.adapters.CharactersAdapter
import com.dionataferraz.presentation.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.android.ext.android.inject

class CharactersFragment : BindingBaseFragment<FragmentCharactersBinding>() {

    //TODO: Add infinity scroll

    override val layoutId: Int = R.layout.fragment_characters

    private val viewModel: CharactersViewModel by inject()

    override val loadVm: (FragmentCharactersBinding) -> Unit = { binding ->
        binding.vm = viewModel
    }

    override fun initializeUI() {
        rv_characters.adapter = CharactersAdapter { character ->
            CharacterDetailFragment.run { showFragment(newInstance(character), TAG) }
        }
    }

    override fun initializeObservables() {
        viewModel.run {
            closeKeyboard().observe(viewLifecycleOwner, Observer { shouldCloseKeyboard ->
                if (shouldCloseKeyboard) {
                    activity?.hideKeyboard(view)
                }
            })

            error.observe(viewLifecycleOwner, Observer { message -> activity?.showSnackbar(message) })
        }
    }
}
