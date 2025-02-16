package com.example.myfoods.Screens.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myfoods.Api.RetrofitsInstance
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.R
import com.example.myfoods.Repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    var repository: MealRepo = MealRepo()
    var _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal>
        get() = _randomMeal

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
}