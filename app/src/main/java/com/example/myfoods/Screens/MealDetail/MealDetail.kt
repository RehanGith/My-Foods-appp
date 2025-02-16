package com.example.myfoods.Screens.MealDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myfoods.Model.Meal
import com.example.myfoods.R
import com.example.myfoods.databinding.ActivityMealDetailesBinding


class MealDetail : Fragment(R.layout.activity_meal_detailes) {
    private lateinit var binding: ActivityMealDetailesBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val meal = arguments?.getSerializable("meal") as Meal?
        if (meal != null) {
            viewModelFactory= DetailViewModelFactory(meal)
            viewModel = ViewModelProvider(this , viewModelFactory)[DetailViewModel::class.java]
        } else {
            throw IllegalArgumentException("Meal cannot be null")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMealDetailesBinding.bind(view)
        showLoading()
        viewModel.meal.observe(viewLifecycleOwner) {
            setTextsInViews(it)
            stopLoading()
        }
    }

    private fun setTextsInViews(meal: Meal) {
        binding.apply {
            tvInstructions.text = getString(R.string.instructions)
            tvContent.text = meal.strInstructions
            tvAreaInfo.visibility = View.VISIBLE
            tvCategoryInfo.visibility = View.VISIBLE
            tvAreaInfo.text = getString(R.string.AreaInfo, tvAreaInfo.text, meal.strArea)
            tvCategoryInfo.text =
                getString(R.string.catagory_info, tvCategoryInfo.text, meal.strCategory)
            imgYoutube.visibility = View.VISIBLE
            collapsingToolbar.title = meal.strMeal
            Glide.with(this@MealDetail)
                .load(meal.strMealThumb ?: R.drawable.ic_launcher_foreground)
                .into(imgMealDetail)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
        binding.imgYoutube.visibility = View.INVISIBLE
    }


    private fun stopLoading() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE

        binding.imgYoutube.visibility = View.VISIBLE

    }

}