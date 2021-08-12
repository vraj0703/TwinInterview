package com.example.twininterview.android.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.twininterview.android.R
import com.example.twininterview.android.databinding.FragmentHomeBinding
import com.example.twininterview.android.ui.base.BaseFragment
import com.example.twininterview.android.extensions.observers
import com.example.twininterview.android.extensions.observe
import com.example.twininterview.android.data.domain.food.model.Food
import com.example.twininterview.android.ui.home.adapter.FoodAdapter
import androidx.appcompat.widget.SearchView

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    override fun init(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        observers(viewModel) {
            observe(loaded) { it?.let { if (it) viewModel.getFoods() } }
            observe(displayFood) { it?.let { displayList(it) } }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.load()
        binding.model = viewModel
    }

    private fun displayList(list: List<Food>) {
        binding.foodList.apply {
            adapter?.let {
                it as FoodAdapter
                it.update(list)
            } ?: run { adapter = FoodAdapter(list) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchFood(newText ?: "")
                return true
            }
        })
    }
}
