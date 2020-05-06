package com.dionataferraz.marvel_comics.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dionataferraz.marvel_comics.R
import com.dionataferraz.marvel_comics.binding.RecyclerViewBinding.BindableAdapter
import com.dionataferraz.marvel_comics.databinding.ItemCommonBinding
import com.dionataferraz.presentation.model.CommonItemPresentation

class ItemsAdapter : ListAdapter<CommonItemPresentation, ItemsAdapter.ViewHolder>(ComicListDiffCallback()),
    BindableAdapter<List<CommonItemPresentation>> {

    override fun setData(data: List<CommonItemPresentation>?) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_common,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position].let { currentCharacter ->
            holder.binding.run {
                item = currentCharacter
                executePendingBindings()
            }
        }
    }

    inner class ViewHolder(val binding: ItemCommonBinding) : RecyclerView.ViewHolder(binding.root)

    private class ComicListDiffCallback : DiffUtil.ItemCallback<CommonItemPresentation>() {

        override fun areItemsTheSame(oldItem: CommonItemPresentation, newItem: CommonItemPresentation): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CommonItemPresentation, newItem: CommonItemPresentation): Boolean =
            oldItem == newItem

    }

}
