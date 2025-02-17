package com.example.myfoods.Repository

import com.example.myfoods.Api.RetrofitsInstance
import com.example.myfoods.Database.MealDatabase
import com.example.myfoods.Model.Meal

class MealRepo() {
    fun getRandomMeal() = RetrofitsInstance.api.getRandomMeal()
    fun getMealDetails(id:String) = RetrofitsInstance.api.getMealDetails(id)
    fun searchMealsByCategory(category :String) = RetrofitsInstance.api.getCategoryMeals(category)
    fun getCategories() = RetrofitsInstance.api.getCategories()
    fun searchMealsByName(name:String) = RetrofitsInstance.api.searchMeals(name)
}