package com.example.myfoods.screens.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
    private lateinit var viewModel : ViewModelForCategories
    private lateinit var categoriesAdapter : CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoriesBinding.bind(view)
        viewModel = ViewModelProvider(this)[ViewModelForCategories::class.java]
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