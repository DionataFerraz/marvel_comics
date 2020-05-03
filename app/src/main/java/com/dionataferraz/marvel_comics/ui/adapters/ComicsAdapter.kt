package com.dionataferraz.marvel_comics.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.databinding.ItemComicBinding
import com.dionataferraz.presentation.model.ComicPresentation

class ComicsAdapter : ListAdapter<ComicPresentation, ComicsAdapter.ViewHolder>(ComicListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comic,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position].let { promoLuckyNumber ->
            holder.binding.run {
                comic = promoLuckyNumber
                executePendingBindings()
            }
        }
    }

    inner class ViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)

    private class ComicListDiffCallback : DiffUtil.ItemCallback<ComicPresentation>() {

        override fun areItemsTheSame(oldItem: ComicPresentation, newItem: ComicPresentation): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ComicPresentation, newItem: ComicPresentation): Boolean =
            oldItem == newItem

    }

}
