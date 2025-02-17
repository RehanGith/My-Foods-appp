package com.example.myfoods.Repository

import com.example.myfoods.Database.MealDatabase
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealDB

class DbRepo(private val db: MealDatabase) {

    suspend fun saveMeal(meal: MealDB) =  db.getMealDao().insertMeal(meal)

    suspend fun deleteMeal(meal: MealDB) =  db.getMealDao().deleteMeal(meal)

    fun getAllMeals() = db.getMealDao().getAllMeals()

    suspend fun deleteById(id: String) = db.getMealDao().deleteById(id)

    suspend fun getById(id: String) = db.getMealDao().getMealById(id)
}