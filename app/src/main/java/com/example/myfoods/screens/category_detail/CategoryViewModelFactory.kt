package com.example.myfoods.screens.category_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CategoryViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            Log.d("CategoryViewModel", category)
            return CategoryViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}