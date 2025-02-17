package com.example.myfoods.Screens.CategoryDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoods.Adapter.MealAdapter
import com.example.myfoods.Model.MealX
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.databinding.ActivityCategoriesBinding

class CategoryDetail : Fragment(R.layout.activity_categories), MealAdapter.OnItemViewClick {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var viewModeFactory: CategoryViewModelFactory
    private lateinit var mealAdapter: MealAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        val str = arguments?.getString(Constants.CATEGORY_NAV) ?:  ""
        viewModeFactory = CategoryViewModelFactory(str)
        viewModel = ViewModelProvider(this, viewModeFactory)[CategoryViewModel::class.java]
        initAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoryFoods.observe(viewLifecycleOwner) {
            binding.tvCategoryCount.text =
                 getString(R.string.tvCategoryName, viewModel.catName, it.size.toString())
        }
        viewModel.mealById.observe(viewLifecycleOwner) { meals ->
            if (!meals.isNullOrEmpty()) { // Handle empty case safely
                val meal = meals[0]
                val bundle = Bundle().apply {
                    putSerializable(Constants.MEAL_NAV, meal) // Pass the Meal object (not the list)
                }
                findNavController().navigate(R.id.action_categoryDetail_to_mealDetail, bundle)
            }
        }

    }
    private fun initAdapter() {
        mealAdapter = MealAdapter(this, MealAdapter.SINGLE_MEAL_VIEW_TYPE)
        binding.mealRecyclerview.apply {
            adapter = mealAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            viewModel.categoryFoods.observe(viewLifecycleOwner) {
                (adapter as MealAdapter).differ.submitList(it)
            }
            setHasFixedSize(true)
        }
    }

    override fun onItemClick(meal: MealX) {
        meal.idMeal.let { viewModel.loadMealById(it) }
    }

}