package com.sample.gutenberg.ui.fragment.book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.gutenberg.databinding.BookFragmentBinding
import com.sample.gutenberg.ui.adapter.BookAdapter
import com.sample.gutenberg.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookFragment : BaseFragment() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private val viewModel: BookViewModel by viewModels()
    private lateinit var binding: BookFragmentBinding
    private lateinit var adapter: BookAdapter
    private val args: BookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun initView() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            "Category ${args.categoryId}"

        adapter = BookAdapter(viewModel)
        binding.rvBook.adapter = adapter
        binding.rvBook.layoutManager = GridLayoutManager(requireActivity(), 3)
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is BookState.Idle -> debugLog("Idle State")
                    is BookState.Loading -> if (it.isLoading) debugLog("Loading State True") else debugLog(
                        "Loading State False"
                    )
                    is BookState.Success -> {
                        adapter.submitList(it.list)
                    }
                    is BookState.Error -> errorLog("Error Occurred")
                }
            }
        }

        lifecycleScope.launch {
            viewModel.bookIntent.send(BookIntent.FetchBooks(categoryId = args.categoryId))
        }
    }
}