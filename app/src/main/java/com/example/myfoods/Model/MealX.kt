package com.example.myfoods.Model

import androidx.room.Entity

@Entity(tableName = "favorites")
data class MealX(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)