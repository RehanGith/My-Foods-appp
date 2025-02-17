package com.example.myfoods.Screens.MealDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoods.Database.MealDatabase
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealDB
import kotlinx.coroutines.launch

class DetailViewModel(myMeal : Meal, app : Application) : AndroidViewModel(app)  {
    private val dbRepo = MealDatabase(app)
    private var _meal = MutableLiveData<Meal>()
    private var _mealDB = MutableLiveData<MealDB>()
    lateinit var meal: Meal

    init {
        Log.i("My Test cate 3", "DetailViewModel ${myMeal.idMeal}")
        _meal.value = myMeal
        _mealDB.value = convertMeal()
        Log.i("MY Test cate 4", "DetailViewModel ${_mealDB.value!!.mealId}")
    }
    val mealDB : LiveData<MealDB>
        get() = _mealDB

    private fun convertMeal(): MealDB {
        // Get the current meal value; if it's null, return null immediately
        if (_meal.value == null) {
            return MealDB(0, "", "", "", "", "", "")
        }else{
            meal = _meal.value!!
        }
        return MealDB(
            mealId = meal.idMeal?.toIntOrNull() ?: 0,                    // Convert idMeal to Int, or default to 0
            mealName = meal.strMeal ?: "Unknown",                         // Use strMeal, or "Unknown" if null
            mealCountry = meal.strArea ?: "Unknown",                      // Use strArea as mealCountry, or default to "Unknown"
            mealCategory = meal.strCategory ?: "Unknown",                 // Use strCategory, or "Unknown"
            mealInstruction = meal.strInstructions ?: "No instructions available", // Use strInstructions, or a default message
            mealThumb = meal.strMealThumb ?: "",                          // Use strMealThumb, or empty string if null
            mealYoutubeLink = meal.strYoutube ?: ""                       // Use strYoutube, or empty string if null
        )
    }




    fun saveMeal() {
        _mealDB.value?.let {
            viewModelScope.launch {
                dbRepo.getMealDao().insertMeal(it)
            }
        }
    }





}