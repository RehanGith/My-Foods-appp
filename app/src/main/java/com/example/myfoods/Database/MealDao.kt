package com.example.myfoods.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myfoods.Model.Meal

@Dao
interface MealDao {
    @Insert
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(mael : Meal)

    @Query("SELECT * FROM Meal")
    fun getAllMeals(): LiveData<List<Meal>>

}