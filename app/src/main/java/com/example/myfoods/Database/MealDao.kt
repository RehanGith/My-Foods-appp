package com.example.myfoods.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealDB

@Dao
interface MealDao {
    @Insert
    suspend fun insertMeal(meal: MealDB)

    @Delete
    suspend fun deleteMeal(mael : MealDB)

    @Query("SELECT * FROM meal_information")
    fun getAllMeals(): LiveData<List<MealDB>>
    @Query("SELECT * FROM meal_information WHERE mealId = :id")
    suspend fun getMealById(id: String): MealDB?




}