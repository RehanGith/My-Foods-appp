package com.example.myfoods.Screens.CategoryDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealX
import com.example.myfoods.Model.PopularMeal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.Repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel(category : String) : ViewModel() {
    private val repo = MealRepo()
    private val _categoryFoods = MutableLiveData<List<MealX>>()
    private val _mealById = MutableLiveData<List<Meal>>()
    val mealById : LiveData<List<Meal>>
        get() = _mealById
    val categoryFoods : LiveData<List<MealX>>
        get() = _categoryFoods
    val catName = category

    init {
        loadCategoryFoods()
    }
    private fun loadCategoryFoods() {
        Log.d("TEST to load category", catName)
        repo.searchMealsByCategory(catName).enqueue(object  : Callback<PopularMeal> {
            override fun onResponse(p0: Call<PopularMeal>, response: Response<PopularMeal>) {
                if(response.body() != null) {
                        val xMeal : List<MealX> = response.body()!!.meals
                        Log.d("TEST", "meal id ${xMeal[0].idMeal} name ${xMeal.size}")
                        _categoryFoods.value = xMeal

                }
            }
            override fun onFailure(p0: Call<PopularMeal>, p1: Throwable) {
                Log.d("TEST", p1.message.toString())
            }
        })
    }
    fun loadMealById(id: String) {
        repo.getMealDetails(id).enqueue(object : Callback<RandomMeal> {
            override fun onResponse(p0: Call<RandomMeal>, response: Response<RandomMeal>) {
                if (response.body() != null) {
                    _mealById.value = response.body()!!.meals
                    Log.d("TEST id", "meal id ${_mealById.value}")
                }
            }

            override fun onFailure(p0: Call<RandomMeal>, p1: Throwable) {
                Log.d("TEST", p1.message.toString())
            }
        })
    }


}