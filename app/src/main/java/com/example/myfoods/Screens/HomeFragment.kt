package com.example.myfoods.Screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myfoods.Api.RetrofitsInstance
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val randomMeal = RetrofitsInstance.api.getRandomMeal().enqueue(object : Callback<RandomMeal> {
            override fun onResponse(p0: Call<RandomMeal>, p1: Response<RandomMeal>) {
                if(p1.body() != null){
                    val randomMeal: Meal = p1.body()!!.meals[0]
                    Log.d("TEST", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")
                    Glide.with(this@HomeFragment).load(randomMeal.strMealThumb ?: R.drawable.ic_launcher_foreground).into(binding.imgRandomMeal)
                } else {
                    return
                }
            }

            override fun onFailure(p0: Call<RandomMeal>, p1: Throwable) {
                Log.d("TEST", p1.message.toString())
            }

        })

    }
}