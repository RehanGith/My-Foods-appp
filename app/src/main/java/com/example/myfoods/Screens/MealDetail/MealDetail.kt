package com.example.myfoods.Screens.MealDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfoods.Model.Meal
import com.example.myfoods.R
import com.example.myfoods.databinding.ActivityMealDetailesBinding


class MealDetail : Fragment(R.layout.activity_meal_detailes) {
    lateinit var binding: ActivityMealDetailesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMealDetailesBinding.bind(view)
        var myMeal : Meal? = null
        val meal = arguments?.getSerializable("meal") as Meal?
        if (meal != null) {
            myMeal = meal
            updateUI(myMeal)
        }
    }

    private fun updateUI(meal: Meal) {

    }

}