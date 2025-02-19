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
import com.bumptech.glide.Glide
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.adapter.FavAdapter
import com.example.myfoods.adapter.MealAdapter
import com.example.myfoods.databinding.FragmentMyBinding
import com.example.myfoods.model.MealDB

class MyFragment : Fragment(R.layout.fragment_my), MealAdapter.OnItemViewClick {
    lateinit var binding: FragmentMyBinding
    private val searchViewModel: MyViewModel by viewModels()
    private lateinit var myAdapter: MealAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBinding.bind(view)

        binding.mySearch = searchViewModel
        binding.lifecycleOwner = this
        setUpRecyclerView()
        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            myAdapter.differ.submitList(it)
        }
    }
    fun setUpRecyclerView() {
        myAdapter = MealAdapter(this, MealAdapter.SINGLE_MEAL_VIEW_TYPE)
        binding.rvFav.apply {
            adapter = myAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onFavClick(meal: MealDB) {
        TODO("Not yet implemented")
    }

}