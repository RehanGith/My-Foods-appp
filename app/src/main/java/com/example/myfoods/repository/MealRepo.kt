package com.example.myfoods.repository

import com.example.myfoods.api.RetrofitsInstance

class MealRepo() {
    fun getRandomMeal() = RetrofitsInstance.api.getRandomMeal()
    fun getMealDetails(id:String) = RetrofitsInstance.api.getMealDetails(id)
    fun searchMealsByCategory(category :String) = RetrofitsInstance.api.getCategoryMeals(category)
    fun getCategories() = RetrofitsInstance.api.getCategories()
    fun searchMealsByName(name:String) = RetrofitsInstance.api.searchMeals(name)
}