package com.sample.gutenberg.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.gutenberg.data.db.entity.Book
import com.sample.gutenberg.databinding.LayoutBookItemBinding
import com.sample.gutenberg.ui.fragment.book.BookViewModel
import com.sample.gutenberg.ui.fragment.category.CategoryViewModel

class BookAdapter(private val viewModel: BookViewModel) :
    ListAdapter<Book, BookAdapter.ViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(viewModel, getItem(position))

    class ViewHolder(private val binding: LayoutBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BookViewModel, book: Book) {
            binding.viewModel = viewModel
            binding.book = book
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = LayoutBookItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
}