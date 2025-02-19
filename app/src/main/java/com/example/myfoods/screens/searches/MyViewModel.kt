package com.example.myfoods.screens.searches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.model.Meal
import com.example.myfoods.model.RandomMeal
import com.example.myfoods.repository.MealRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    var query = MutableLiveData<String>("")
    private var _searchResult = MutableLiveData<List<Meal>>()
    private val repository = MealRepo()
    val searchResult: LiveData<List<Meal>>
        get() = _searchResult

    fun searchByName() {
        Log.d("SearchViewModel 1", query.value.toString())
        if (query.value.isNullOrEmpty()) return
        repository.searchMealsByName(query.value!!).enqueue(object : Callback<RandomMeal> {
            override fun onResponse(p0: Call<RandomMeal>, response: Response<RandomMeal>) {
                if (response.isSuccessful) {
                    _searchResult.value = response.body()?.meals
                }
            }
            override fun onFailure(p0: Call<RandomMeal>, p1: Throwable) {
                Log.d("SearchViewModel", p1.message.toString())
            }
        })
    }

}