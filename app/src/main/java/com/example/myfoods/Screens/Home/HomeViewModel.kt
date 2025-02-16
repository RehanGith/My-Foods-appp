package com.example.myfoods.Screens.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myfoods.Api.RetrofitsInstance
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealX
import com.example.myfoods.Model.PopularMeal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.R
import com.example.myfoods.Repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var repository: MealRepo = MealRepo()
    private var _randomMeal = MutableLiveData<Meal>()
    private var _popularItems = MutableLiveData<List<Meal>>()
    private var _mealById = MutableLiveData<List<Meal>>()
    val randomMeal: LiveData<Meal>
        get() = _randomMeal
    val popularItems: MutableLiveData<List<Meal>>
        get() = _popularItems
    val mealById: LiveData<List<Meal>>
        get() = _mealById
    //fun for loading random meal in view
    fun loadRandomMeal() {
        repository.getRandomMeal().enqueue(object : Callback<RandomMeal> {
            override fun onResponse(call: Call<RandomMeal>, response: Response<RandomMeal>) {
                if (response.body() != null) {
                    val rMeal = response.body()!!.meals[0]
                    Log.d("TEST", "meal id ${rMeal.idMeal} name ${rMeal.strMeal}")
                    _randomMeal.value = rMeal
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<RandomMeal>, t: Throwable) {
                Log.d("TEST", t.message.toString())
            }
        })
    }
    //fun for loading category meals by category id
    fun loadPopularFoods(category : String)  {
        repository.searchMealsByCategory(category).enqueue(object  : Callback<PopularMeal> {
            override fun onResponse(p0: Call<PopularMeal>, response: Response<PopularMeal>) {
                if(response.body() != null) {
                    val xMeal : List<MealX> = response.body()!!.meals
                    Log.d("TEST", "meal id ${xMeal[0].idMeal} name ${xMeal.size}")
                    val meals: List<Meal> = xMeal.map { mealX ->
                        Meal(
                            idMeal = mealX.idMeal,
                            strMeal = mealX.strMeal,
                            strMealThumb = mealX.strMealThumb
                            // map other properties if needed
                        )
                    }
                    _popularItems.value = meals
                }
            }
            override fun onFailure(p0: Call<PopularMeal>, p1: Throwable) {
                Log.d("TEST", p1.message.toString())
            }
        })
    }
    //for load by the meal id
    fun loadMealById(id: String) {
        repository.getMealDetails(id).enqueue(object : Callback<RandomMeal> {
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