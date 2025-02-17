package com.example.myfoods.Screens.Category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.Model.Category
import com.example.myfoods.Model.CategoryMeal
import com.example.myfoods.Model.MealX
import com.example.myfoods.Model.PopularMeal
import com.example.myfoods.Repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelForCategories: ViewModel() {
    private val repo = MealRepo()
    private var _categories=  MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    fun loadCategories() {
        repo.getCategories().enqueue(object : Callback<CategoryMeal> {
            override fun onResponse(p0: Call<CategoryMeal>, response: Response<CategoryMeal>) {
                if (response.body() != null) {
                    _categories.value = response.body()!!.categories
                }
            }

            override fun onFailure(p0: Call<CategoryMeal>, p1: Throwable) {
                Log.d("TEST", p1.message.toString())
            }

        })
    }


}