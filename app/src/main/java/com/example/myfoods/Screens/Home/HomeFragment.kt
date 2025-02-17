package com.example.myfoods.Screens.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myfoods.Adapter.CategoryAdapter
import com.example.myfoods.Adapter.MealAdapter
import com.example.myfoods.Api.RetrofitsInstance.Companion.api
import com.example.myfoods.Model.Category
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.MealX
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class HomeFragment : Fragment(R.layout.fragment_home), MealAdapter.OnItemViewClick, CategoryAdapter.OnCategoryClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var mealAdapter: MealAdapter
    private lateinit var categoryAdapter: CategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        showLoadingCase()

        //loading random meal and displaying it
        viewModel.loadRandomMeal()
        viewModel.loadPopularFoods("Seafood")
        viewModel.loadCategories()

        setUpCategoryRecyclerView()
        setUpRecyclerView()
        observeData()
        //observing random meal and displaying it
        binding.imgRandomMeal.setOnClickListener {
            viewModel.randomMeal.observe(viewLifecycleOwner) {
                onMealClick(it)
            }
        }
        viewModel.mealById.observe(viewLifecycleOwner) {meals ->
            if(!meals.isNullOrEmpty()) {
                onMealClick(meals[0])
            }
        }

    }
    private fun observeData() {
        var isRandomMealLoaded = false
        var isPopularItemsLoaded = false
        var isCategoriesLoaded = false

        viewModel.randomMeal.observe(viewLifecycleOwner) {
            Glide.with(this@HomeFragment)
                .load(it.strMealThumb)
                .into(binding.imgRandomMeal)
            isRandomMealLoaded = true
            checkIfDataIsLoaded(isRandomMealLoaded, isPopularItemsLoaded, isCategoriesLoaded)
        }

        viewModel.popularItems.observe(viewLifecycleOwner) {
            mealAdapter.differ.submitList(it)
            isPopularItemsLoaded = true
            checkIfDataIsLoaded(isRandomMealLoaded, isPopularItemsLoaded, isCategoriesLoaded)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", "Categories loaded: ${it.size}")
            categoryAdapter.differ.submitList(it)
            binding.categoryCard.post {
                binding.categoryCard.requestLayout()
                binding.root.requestLayout()
            }
            isCategoriesLoaded = true
            checkIfDataIsLoaded(isRandomMealLoaded, isPopularItemsLoaded, isCategoriesLoaded)
        }
    }

    private fun checkIfDataIsLoaded(isRandomMealLoaded: Boolean, isPopularItemsLoaded: Boolean, isCategoriesLoaded: Boolean) {
        if (isRandomMealLoaded && isPopularItemsLoaded && isCategoriesLoaded) {
            cancelLoadingCase()
        }
    }
    //navigate to meal detail fragment
    private fun onMealClick(rMeal: Meal) {
        val bundle = Bundle().apply {
            putSerializable(Constants.MEAL_NAV, rMeal)
        }
        findNavController().navigate(R.id.action_homeFragment_to_mealDetail, bundle)
    }

    //on any item click in recycler view except random meal
    override fun onItemClick(meal: MealX) {
        meal.idMeal.let { viewModel.loadMealById(it) }

    }

    private fun setUpRecyclerView() {
        mealAdapter = MealAdapter(this, MealAdapter.MOST_POPULAR_VIEW_TYPE)
        binding.recViewMealsPopular.apply {
            adapter = mealAdapter
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

        }
    }
    private fun setUpCategoryRecyclerView() {
        categoryAdapter = CategoryAdapter(this)
        binding.categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onCategoryClick(category: Category) {
        Log.d("Test HomeFragment", "onCategoryClick: ${category.strCategory}")
        val bundle = Bundle().apply {
            putString(Constants.CATEGORY_NAV, category.strCategory)
        }
        findNavController().navigate(R.id.action_homeFragment_to_categoryDetail, bundle)
    }
    private fun showLoadingCase() {
        binding.apply {
            header.visibility = View.INVISIBLE

            tvWouldLikeToEat.visibility = View.INVISIBLE
            randomMeal.visibility = View.INVISIBLE
            tvOverPupItems.visibility = View.INVISIBLE
            recViewMealsPopular.visibility = View.INVISIBLE
            tvCategory.visibility = View.INVISIBLE
            categoryCard.visibility = View.INVISIBLE
            loadingGif.visibility = View.VISIBLE
            rootHome.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.g_loading))

        }
    }

    private fun cancelLoadingCase() {
        binding.apply {
            header.visibility = View.VISIBLE
            tvWouldLikeToEat.visibility = View.VISIBLE
            randomMeal.visibility = View.VISIBLE
            tvOverPupItems.visibility = View.VISIBLE
            recViewMealsPopular.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            categoryCard.visibility = View.VISIBLE
            loadingGif.visibility = View.INVISIBLE
            rootHome.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

        }
    }
}
