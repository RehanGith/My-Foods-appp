package com.example.myfoods.Repository

import com.example.myfoods.Api.RetrofitsInstance

class MealRepo {
    fun getRandomMeal() = RetrofitsInstance.api.getRandomMeal()
    fun getMealDetails(id:String) = RetrofitsInstance.api.getMealDetails(id)
    fun searchMealsByCategory(category :String) = RetrofitsInstance.api.getCategoryMeals(category)
    fun getCategories() = RetrofitsInstance.api.getCategories()
}