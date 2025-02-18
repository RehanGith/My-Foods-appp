package com.example.myfoods.Screens.Search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.Repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private var _searchText = MutableLiveData<Meal>()
    private val repo = MealRepo()

    val searchText: LiveData<Meal>
            get() = _searchText

    fun setSearchText(text: String) {
        Log.i("SearchViewModel", text)
        repo.searchMealsByName(text).enqueue(object : Callback<RandomMeal>{
            override fun onResponse(p0: Call<RandomMeal>, p1: Response<RandomMeal>) {
                val meal = p1.body()
                Log.i("SearchViewModel", meal!!.meals[0].strMeal.toString())
                _searchText.value = meal.meals[0]
            }

            override fun onFailure(p0: Call<RandomMeal>, p1: Throwable) {
                Log.i("SearchViewModel", p1.message.toString())
            }
        })
    }



}
