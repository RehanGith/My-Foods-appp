package com.example.myfoods.Api


import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.RandomMeal
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("random.php")
    fun getRandomMeal(): Call<RandomMeal>
}



















