package com.example.myfoods.repository

import com.example.myfoods.database.MealDatabase
import com.example.myfoods.model.MealDB

class DbRepo(private val db: MealDatabase) {

    suspend fun saveMeal(meal: MealDB) =  db.getMealDao().insertMeal(meal)

    suspend fun deleteMeal(meal: MealDB) =  db.getMealDao().deleteMeal(meal)

    fun getAllMeals() = db.getMealDao().getAllMeals()


    suspend fun getById(id: String) = db.getMealDao().getMealById(id)
}