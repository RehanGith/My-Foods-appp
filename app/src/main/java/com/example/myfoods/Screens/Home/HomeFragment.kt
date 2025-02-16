package com.example.myfoods.Screens.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myfoods.Api.RetrofitsInstance.Companion.api
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.R
import com.example.myfoods.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //loading random meal and displaying it
        viewModel.loadRandomMeal()
        observeRandomMeal()

        binding.imgRandomMeal.setOnClickListener {
            viewModel.randomMeal.observe(viewLifecycleOwner) { meal ->
                meal?.let { it1 -> onMealClick(it1) }
            }
        }
    }

    private fun onMealClick(rMeal: Meal) {
        val bundle = Bundle().apply {
            putSerializable("meal", rMeal)
        }
        findNavController().navigate(R.id.action_homeFragment_to_mealDetail, bundle)
    }
    private fun observeRandomMeal() {
        viewModel.randomMeal.observe(viewLifecycleOwner) {
            Glide.with(this@HomeFragment)
                .load(it.strMealThumb)
                .into(binding.imgRandomMeal)
        }
    }
}
