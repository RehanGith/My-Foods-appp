package com.example.myfoods.screens.searches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.databinding.FragmentMyBinding

class MyFragment : Fragment(R.layout.fragment_my) {
    lateinit var binding: FragmentMyBinding
    private val searchViewModel: MyViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBinding.bind(view)

        binding.mySearch = searchViewModel
        binding.lifecycleOwner = this

        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            binding.searchedMealCard.visibility = View.VISIBLE
            Log.d("Search", "onViewCreated: ${it.strMeal}")
            binding.tvSearchedMeal.text = it.strMeal ?: ""
            Glide.with(requireContext())
                .load(it.strMealThumb)
                .into(binding.imgSearchedMeal)

        }
        binding.imgSearchedMeal.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(Constants.MEAL_NAV, searchViewModel.searchResult.value)
            }
            findNavController().navigate(R.id.action_myFragment_to_mealDetail, bundle)
        }
    }

}