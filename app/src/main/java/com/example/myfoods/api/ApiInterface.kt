package com.example.myfoods.api


import com.example.myfoods.model.CategoryMeal
import com.example.myfoods.model.PopularMeal
import com.example.myfoods.model.RandomMeal
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



















