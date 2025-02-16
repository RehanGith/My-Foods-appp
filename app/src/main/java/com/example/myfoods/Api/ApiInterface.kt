package com.example.myfoods.Api


import androidx.lifecycle.LiveData
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.PopularMeal
import com.example.myfoods.Model.RandomMeal
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("random.php")
    fun getRandomMeal(): Call<RandomMeal>
    @GET("lookup.php?")
    fun getMealDetails(id:String): Call<Meal>
    @GET("filter.php?")
    fun getSeafoodMeals(): LiveData<PopularMeal>
}



















