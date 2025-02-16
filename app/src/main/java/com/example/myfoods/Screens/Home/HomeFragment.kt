package com.example.myfoods.Screens.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
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
    lateinit var binding: FragmentHomeBinding
    var randomMeal: Meal? = null // Change to nullable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        api.getRandomMeal().enqueue(object : Callback<RandomMeal> {
            override fun onResponse(call: Call<RandomMeal>, response: Response<RandomMeal>) {
                if (response.body() != null) {
                    randomMeal = response.body()!!.meals[0]
                    Log.d("TEST", "meal id ${randomMeal?.idMeal} name ${randomMeal?.strMeal}")
                    Glide.with(this@HomeFragment)
                        .load(randomMeal?.strMealThumb ?: R.drawable.ic_launcher_foreground)
                        .into(binding.imgRandomMeal)
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<RandomMeal>, t: Throwable) {
                Log.d("TEST", t.message.toString())
            }
        })

        binding.imgRandomMeal.setOnClickListener {
            randomMeal?.let { meal ->
                onMealClick(meal)
            }
        }
    }

    private fun onMealClick(rMeal: Meal) {
        val bundle = Bundle().apply {
            putSerializable("meal", rMeal)
        }
        findNavController().navigate(R.id.action_homeFragment_to_mealDetail, bundle)
    }
}
