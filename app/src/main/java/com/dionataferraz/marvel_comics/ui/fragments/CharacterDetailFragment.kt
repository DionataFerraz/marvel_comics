package com.dionataferraz.marvel_comics.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.base.BindingBaseFragment
import com.dionataferraz.marvel_comics.databinding.FragmentCharacterDetailBinding
import com.dionataferraz.marvel_comics.extensions.showSnackbar
import com.dionataferraz.marvel_comics.ui.adapters.ItemsAdapter
import com.dionataferraz.presentation.CharacterDetailViewModel
import com.dionataferraz.presentation.model.CharacterPresentation
import kotlinx.android.synthetic.main.fragment_character_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : BindingBaseFragment<FragmentCharacterDetailBinding>() {

    //TODO: Add infinity scroll

    override val layoutId: Int = R.layout.fragment_character_detail

    private val viewModel: CharacterDetailViewModel by inject {
        parametersOf(arguments?.getSerializable(CHARACTER_TAG))
    }

    override val loadVm: (FragmentCharacterDetailBinding) -> Unit = { binding ->
        binding.vm = viewModel
    }

    override fun initializeUI() {
        rv_comics.adapter = ItemsAdapter()
        rv_series.adapter = ItemsAdapter()
    }

    override fun initializeObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer { message -> activity?.showSnackbar(message) })
    }

    companion object {
        val TAG = CharacterDetailFragment::javaClass.name
        private const val CHARACTER_TAG = "characterPresentation"

        @JvmStatic
        fun newInstance(characterPresentation: CharacterPresentation) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CHARACTER_TAG, characterPresentation)
                }
            }
    }
}
