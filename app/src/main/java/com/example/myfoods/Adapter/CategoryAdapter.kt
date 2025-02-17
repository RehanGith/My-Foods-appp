package com.example.myfoods.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoods.Model.Category
import com.example.myfoods.R
import com.example.myfoods.databinding.CategoryCardBinding
import com.example.myfoods.databinding.MostPopularCardBinding
import com.example.myfoods.databinding.SingleMealCardBinding

class CategoryAdapter(private val listener: OnCategoryClickListener): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(binding: CategoryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val catName = binding.tvCategoryName
        val categoryImg = binding.imgCategory
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    val differCallBack = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.catName.text = category.strCategory
        Glide.with(holder.itemView).load(category.strCategoryThumb ?: R.drawable.ic_launcher_foreground).into(holder.categoryImg)
        holder.itemView.setOnClickListener {
            Log.d("Test Interface", "onBindViewHolder: ${category.strCategory}")
            listener.onCategoryClick(category)
        }
    }
}