package com.example.myfoods.screens.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoods.adapter.CategoryAdapter
import com.example.myfoods.model.Category
import com.example.myfoods.R
import com.example.myfoods.Util.Constants
import com.example.myfoods.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment(R.layout.fragment_categories), CategoryAdapter.OnCategoryClickListener {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel : ViewModelForCategories by viewModels()
    private lateinit var categoriesAdapter : CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoriesBinding.bind(view)
        binding.categoryViewModel = viewModel
        binding.lifecycleOwner = this
        setUpRecyclerView()
        viewModel.categories.observe(viewLifecycleOwner) {
            categoriesAdapter.differ.submitList(it)
        }


    }
    private fun setUpRecyclerView() {
        categoriesAdapter = CategoryAdapter(this)
        binding.rvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onCategoryClick(category: Category) {
        val bundle = Bundle().apply {
            putString(Constants.CATEGORY_NAV, category.strCategory)
        }
        findNavController().navigate(R.id.action_categoriesFragment_to_categoryDetail, bundle)
    }
}