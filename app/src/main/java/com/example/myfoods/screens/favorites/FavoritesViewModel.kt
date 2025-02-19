package com.example.myfoods.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myfoods.database.MealDatabase
import com.example.myfoods.model.MealDB
import com.example.myfoods.repository.DbRepo
import kotlinx.coroutines.launch

class FavoritesViewModel(app : Application): AndroidViewModel(app) {

    private val dbRepo = DbRepo(MealDatabase(app))

    fun getFavorites() = dbRepo.getAllMeals()
    fun deleteMeal(meal : MealDB) {
        viewModelScope.launch {
            dbRepo.deleteMeal(meal)
        }
    }
    fun addToFavorites(meal : MealDB) {
        viewModelScope.launch {
            dbRepo.saveMeal(meal)
        }
    }

}