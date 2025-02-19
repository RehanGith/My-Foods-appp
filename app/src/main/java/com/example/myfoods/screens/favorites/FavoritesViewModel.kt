package com.example.myfoods.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myfoods.database.MealDatabase
import com.example.myfoods.repository.DbRepo

class FavoritesViewModel(app : Application): AndroidViewModel(app) {

    private val dbRepo = DbRepo(MealDatabase(app))

    fun getFavorites() = dbRepo.getAllMeals()

}