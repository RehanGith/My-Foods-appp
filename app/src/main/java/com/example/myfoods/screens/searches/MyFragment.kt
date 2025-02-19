package com.example.myfoods.screens.searches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.adapter.FavAdapter
import com.example.myfoods.adapter.MealAdapter
import com.example.myfoods.adapter.SearchAdapter
import com.example.myfoods.databinding.FragmentMyBinding
import com.example.myfoods.model.Meal
import com.example.myfoods.model.MealDB

class MyFragment : Fragment(R.layout.fragment_my), SearchAdapter.onSearchItemClick {
    lateinit var binding: FragmentMyBinding
    private val searchViewModel: MyViewModel by viewModels()
    private lateinit var myAdapter: SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBinding.bind(view)

        binding.mySearch = searchViewModel
        binding.lifecycleOwner = this
        setUpRecyclerView()
        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            Log.d("SearchFragment  ", "onViewCreated: ${it.size}")
            myAdapter.differ.submitList(it)
            Log.d("SearchFragment  ", "onViewCreated: ${myAdapter.differ.currentList.size}")
        }
    }
    fun setUpRecyclerView() {
        myAdapter = SearchAdapter(this)
        binding.rvFav.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onItemClick(meal: Meal) {
        val bundle = Bundle().apply {
            putSerializable(Constants.MEAL_NAV, meal)
        }
        findNavController().navigate(R.id.action_myFragment_to_mealDetail, bundle)
    }


}