package com.example.myfoods.screens.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoods.model.Category
import com.example.myfoods.model.CategoryMeal
import com.example.myfoods.repository.MealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelForCategories: ViewModel() {
    private val repo = MealRepo()
    private var _categories=  MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories
    init {
        loadCategories()
    }
    private fun loadCategories() {
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