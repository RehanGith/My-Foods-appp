package com.example.myfoods.Screens.MealDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealDB

class DetailViewModel(myMeal : Meal, app : Application) : AndroidViewModel(app)  {

    private var _meal = MutableLiveData<Meal>()
    private var _mealDB = MutableLiveData<MealDB>()

    init {
        _meal.value = myMeal
    }
    val mealDB : LiveData<MealDB>
        get() = _mealDB

    private fun convertMeal() : MealDB? {
        val meal = _meal.value?.strYoutube?.let {
            _meal.value?.strMealThumb?.let { it1 ->
                _meal.value?.strInstructions?.let { it2 ->
                    _meal.value?.strCategory?.let { it3 ->
                        _meal.value?.strArea?.let { it4 ->
                            _meal.value?.strMeal?.let { it5 ->
                                _meal.value?.idMeal?.let { it6 ->
                                    MealDB(
                                        it6.toInt(),
                                        it5,
                                        it4,
                                        it3,
                                        it2,
                                        it1,
                                        it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        return meal
    }



}