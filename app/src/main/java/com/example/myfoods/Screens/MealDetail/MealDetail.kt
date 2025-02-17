package com.example.myfoods.Screens.MealDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myfoods.Database.MealDatabase
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealDB
import com.example.myfoods.R
import com.example.myfoods.Repository.DbRepo
import com.example.myfoods.Util.Constants
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
        val meal = arguments?.getSerializable(Constants.MEAL_NAV) as Meal?
        if (meal != null) {
            viewModelFactory= DetailViewModelFactory(meal, requireActivity().application)
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
        viewModel.mealDB.observe(viewLifecycleOwner) {
            setTextsInViews(it)
            stopLoading()
        }
        binding.imgYoutube.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.mealDB.value?.mealYoutubeLink))
            startActivity(intent)
        }

        binding.btnSave.setOnClickListener {
            binding.btnSave.resources.getDrawable(R.drawable.ic_saved)
            viewModel.saveMeal()
        }
    }

    private fun setTextsInViews(meal: MealDB) {
        binding.apply {
            Glide.with(this@MealDetail)
                .load(meal.mealThumb ?: R.drawable.ic_launcher_foreground)
                .into(imgMealDetail)
            collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
            collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
            tvInstructions.text = getString(R.string.instructions)
            tvContent.text = meal.mealInstruction
            tvAreaInfo.visibility = View.VISIBLE
            tvCategoryInfo.visibility = View.VISIBLE
            tvAreaInfo.text = getString(R.string.AreaInfo, tvAreaInfo.text, meal.mealCountry)
            tvCategoryInfo.text =
                getString(R.string.catagory_info, tvCategoryInfo.text, meal.mealCategory)
            imgYoutube.visibility = View.VISIBLE
            imgYoutube.tag = meal.mealYoutubeLink
            collapsingToolbar.title = meal.mealName

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