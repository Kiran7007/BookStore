package com.sample.gutenberg.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.gutenberg.databinding.CategoryFragmentBinding
import com.sample.gutenberg.ui.adapter.CategoryAdapter
import com.sample.gutenberg.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {

    companion object {
        fun newInstance() = CategoryFragment()
    }

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var binding: CategoryFragmentBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun initView() {
        adapter = CategoryAdapter(viewModel)
        binding.rvCategory.adapter = adapter
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CategoryState.Idle -> debugLog("Idle State")
                    is CategoryState.Loading -> if (it.isLoading) debugLog("Loading State True") else debugLog(
                        "Loading State False"
                    )
                    is CategoryState.Success -> {
                        adapter.submitList(it.list)
                    }
                    is CategoryState.Error -> errorLog("Error Occurred")
                    is CategoryState.NavigateToBooksScreen -> handleNavigation(it.categoryId)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.categoryIntent.send(CategoryIntent.FetchCategory)
        }
    }

    private fun handleNavigation(categoryId: String) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToBookFragment(categoryId)
        findNavController().navigate(action)
    }
}