package com.example.myfoods.screens.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoods.adapter.FavAdapter
import com.example.myfoods.model.Meal
import com.example.myfoods.model.MealDB
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment(R.layout.fragment_favorites), FavAdapter.OnFavClickListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var viewModelFactory: FavViewModelFactory
    private lateinit var favAdapter: FavAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
        viewModelFactory = FavViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]
        setUpRecyclerView()
        viewModel.getFavorites().observe(viewLifecycleOwner) {
            if(it != null) {
                favAdapter.differ.submitList(it)
                binding.tvFavEmpty.visibility = View.INVISIBLE
            }else {
                binding.tvFavEmpty.visibility = View.VISIBLE
            }
        }
    }
    private fun setUpRecyclerView() {
        favAdapter = FavAdapter(this)
        binding.favRecView.apply {
            adapter = favAdapter
            layoutManager  = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
    private fun convertMealDBToMeal(mealDB: MealDB): Meal {
        return Meal(
            dateModified = null,
            idMeal = mealDB.mealId.toString(),
            strArea = mealDB.mealCountry,
            strCategory = mealDB.mealCategory,
            strCreativeCommonsConfirmed = null,
            strDrinkAlternate = null,
            strImageSource = null,
            strIngredient1 = null,
            strIngredient2 = null,
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strIngredient16 = null,
            strIngredient17 = null,
            strIngredient18 = null,
            strIngredient19 = null,
            strIngredient20 = null,
            strInstructions = mealDB.mealInstruction,
            strMeal = mealDB.mealName,
            strMealThumb = mealDB.mealThumb,
            strMeasure1 = null,
            strMeasure2 = null,
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strMeasure16 = null,
            strMeasure17 = null,
            strMeasure18 = null,
            strMeasure19 = null,
            strMeasure20 = null,
            strSource = null,
            strTags = null,
            strYoutube = mealDB.mealYoutubeLink
        )
    }


    override fun onFavClick(meal: MealDB) {
        val convertedMeal = convertMealDBToMeal(meal)
        val bundle = Bundle().apply {
            putSerializable(Constants.MEAL_NAV, convertedMeal)
        }
        findNavController().navigate(R.id.action_favoritesFragment_to_mealDetail, bundle)
    }
}