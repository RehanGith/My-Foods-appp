package com.example.myfoods.Screens.MealDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.Model.Meal

class DetailViewModel(myMeal : Meal, app : Application) : AndroidViewModel(app)  {

    private var _meal = MutableLiveData<Meal>()

    init {
        _meal.value = myMeal
    }
    val meal : LiveData<Meal>
        get() = _meal




}