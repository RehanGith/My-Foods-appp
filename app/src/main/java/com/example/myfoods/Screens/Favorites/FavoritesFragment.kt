package com.example.myfoods.Screens.Favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoods.Adapter.FavAdapter
import com.example.myfoods.Model.MealDB
import com.example.myfoods.R
import com.example.myfoods.databinding.FragmentFavoritesBinding
import com.example.myfoods.databinding.MealCardBinding


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
                Log.d("Favorites", "Favorites: ${it.size}")
                favAdapter.differ.submitList(it)
                Log.d("Favorites", "Favorites: ${favAdapter.differ.currentList.size}")
                binding.tvFavEmpty.visibility = View.INVISIBLE
            }else {
                Log.d("Favorites", "Favorites: Empty")
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

    override fun onFavClick(meal: MealDB) {

    }
}