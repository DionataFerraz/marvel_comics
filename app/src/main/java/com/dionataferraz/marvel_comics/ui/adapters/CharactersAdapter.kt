package com.dionataferraz.marvel_comics.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.binding.RecyclerViewBinding.BindableAdapter
import com.dionataferraz.marvel_comics.databinding.ItemCharacterBinding
import com.dionataferraz.presentation.model.CharacterPresentation

class CharactersAdapter(private val onClick: (CharacterPresentation) -> Unit) :
    ListAdapter<CharacterPresentation, CharactersAdapter.ViewHolder>(CharacterDiffCallback()),
    BindableAdapter<List<CharacterPresentation>> {

    override fun setData(data: List<CharacterPresentation>?) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position].let { currentCharacter ->
            holder.binding.run {
                character = currentCharacter
                executePendingBindings()

                root.setOnClickListener {
                    onClick(currentCharacter)
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    private class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterPresentation>() {

        override fun areItemsTheSame(oldItem: CharacterPresentation, newItem: CharacterPresentation): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CharacterPresentation, newItem: CharacterPresentation): Boolean =
            oldItem == newItem

    }

}
