package com.example.myfoods.Screens.Favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myfoods.Database.MealDatabase
import com.example.myfoods.Repository.DbRepo

class FavoritesViewModel(app : Application): AndroidViewModel(app) {

    private val dbRepo = DbRepo(MealDatabase(app))

    fun getFavorites() = dbRepo.getAllMeals()

}