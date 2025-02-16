package com.example.myfoods.Repository

import com.example.myfoods.Api.RetrofitsInstance

class MealRepo {
    fun getRandomMeal() = RetrofitsInstance.api.getRandomMeal()
}