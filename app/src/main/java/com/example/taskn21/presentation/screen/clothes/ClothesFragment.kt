package com.example.taskn21.presentation.screen.clothes

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taskn21.databinding.FragmentClothesBinding
import com.example.taskn21.presentation.base.BaseFragment
import com.example.taskn21.presentation.event.ClothesEvent
import com.example.taskn21.presentation.extension.showSnackBar
import com.example.taskn21.presentation.screen.clothes.adapter.ClothesRecyclerViewAdapter
import com.example.taskn21.presentation.state.ClothesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ClothesFragment : BaseFragment<FragmentClothesBinding>(FragmentClothesBinding::inflate) {

    private val recyclerAdapter = ClothesRecyclerViewAdapter()
    private val viewModel: ClothesViewModel by viewModels()

    override fun setUp() {
        with(binding.recyclerViewClothes) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
    }

    override fun setUpListeners() {
        recyclerAdapter.onClick = {
            viewModel.onEvent(ClothesEvent.SelectFavouriteEvent(it))
        }
    }

    override fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersStateFlow.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: ClothesState) {
        with(state) {
            clothes?.let {
                recyclerAdapter.submitList(it)
            }

            binding.progressBar.isVisible = isLoading

            errorMessage?.let {
                binding.root.showSnackBar(it)
            }
        }
    }
}