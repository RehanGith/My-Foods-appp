package com.example.myfoods.Api


import androidx.lifecycle.LiveData
import com.example.myfoods.Model.CategoryMeal
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.PopularMeal
import com.example.myfoods.Model.RandomMeal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("random.php")
    fun getRandomMeal(): Call<RandomMeal>
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String): Call<RandomMeal>
    @GET("filter.php?")
    fun getCategoryMeals(@Query("c") category:String): Call<PopularMeal>

    @GET("categories.php")
    fun getCategories(): Call<CategoryMeal>
    @GET("search.php?")
    fun searchMeals(@Query("s") searchQuery:String): Call<RandomMeal>
}



















