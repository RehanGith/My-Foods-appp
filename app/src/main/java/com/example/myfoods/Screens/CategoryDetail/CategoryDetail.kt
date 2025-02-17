package com.example.myfoods.Screens.CategoryDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myfoods.R
import com.example.myfoods.Screens.Home.HomeViewModel
import com.example.myfoods.databinding.ActivityCategoriesBinding

class CategoryDetail : Fragment(R.layout.activity_categories) {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var viewModeFactory: CategoryViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ActivityCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        val str = arguments?.getString("categoryName") ?:  ""
        Log.d("Test str at Category", str)
        viewModeFactory = CategoryViewModelFactory(str)
        viewModel = ViewModelProvider(this, viewModeFactory).get(CategoryViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.categoryFoods.observe(viewLifecycleOwner) {
             binding.tvCategoryCount.text = it.size.toString()

        }

    }

}