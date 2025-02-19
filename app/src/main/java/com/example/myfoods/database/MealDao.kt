package com.example.myfoods.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfoods.model.MealDB

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealDB)

    @Delete
    suspend fun deleteMeal(mael : MealDB)

    @Query("SELECT * FROM meal_information")
    fun getAllMeals(): LiveData<List<MealDB>>
    @Query("SELECT * FROM meal_information WHERE mealId = :id")
    suspend fun getMealById(id: String): MealDB?




}