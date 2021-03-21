package com.sample.gutenberg.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.gutenberg.data.db.entity.Category
import com.sample.gutenberg.databinding.LayoutCategoryItemBinding
import com.sample.gutenberg.ui.fragment.category.CategoryViewModel

class CategoryAdapter(private val viewModel: CategoryViewModel) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(viewModel, getItem(position))

    class ViewHolder(private val binding: LayoutCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CategoryViewModel, category: Category) {
            binding.viewModel = viewModel
            binding.category = category
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = LayoutCategoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
}