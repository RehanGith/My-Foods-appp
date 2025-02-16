package com.example.myfoods.Screens.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myfoods.Adapter.MealAdapter
import com.example.myfoods.Api.RetrofitsInstance.Companion.api
import com.example.myfoods.Model.Meal
import com.example.myfoods.Model.RandomMeal
import com.example.myfoods.R
import com.example.myfoods.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(R.layout.fragment_home), MealAdapter.OnItemViewClick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var mealAdapter: MealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //loading random meal and displaying it
        viewModel.loadRandomMeal()
        viewModel.loadPopularFoods("Seafood")
        setUpRecyclerView()
        //observing random meal and displaying it
        viewModel.randomMeal.observe(viewLifecycleOwner) {
            Glide.with(this@HomeFragment)
                .load(it.strMealThumb)
                .into(binding.imgRandomMeal)
        }
        binding.imgRandomMeal.setOnClickListener {
            viewModel.randomMeal.observe(viewLifecycleOwner) {
                onMealClick(it)
            }
        }


    }
    //navigate to meal detail fragment
    private fun onMealClick(rMeal: Meal) {
        val bundle = Bundle().apply {
            putSerializable("meal", rMeal)
        }
        findNavController().navigate(R.id.action_homeFragment_to_mealDetail, bundle)
    }
    //on any item click in recycler view except random meal
    override fun onItemClick(meal: Meal) {
        Log.d("Test HomeFragment", "onItemClick: ${meal.idMeal}")
        meal.idMeal?.let { viewModel.loadMealById(it) }
        viewModel.mealById.observe(viewLifecycleOwner) {
            onMealClick(it[0])
        }
    }
    private fun setUpRecyclerView() {
        mealAdapter = MealAdapter(this)
        binding.recViewMealsPopular.apply {
            adapter = mealAdapter
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            viewModel.popularItems.observe(viewLifecycleOwner) {
                (adapter as MealAdapter).differ.submitList(it)
            }
            setHasFixedSize(true)
        }
    }
}
