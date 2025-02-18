package com.example.myfoods.Screens.Search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myfoods.Adapter.MealAdapter
import com.example.myfoods.R
import com.example.myfoods.databinding.FragmentSearchBinding


class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        Log.d("SearchFragment", "onViewCreated: ")
        binding.icSearch.setOnClickListener {
            Log.d("SearchText", binding.edSearch.text.toString())
            if(binding.edSearch.text.toString().isNotEmpty()){
                Log.d("SearchText", binding.edSearch.text.toString())
                viewModel.setSearchText(text = binding.edSearch.text.toString())
            }
        }
        viewModel.searchText.observe(viewLifecycleOwner){
            binding.tvSearchedMeal.text = it.strMeal
            Glide.with(requireContext()).load(it.strMealThumb).into(binding.imgSearchedMeal)
        }
    }


}