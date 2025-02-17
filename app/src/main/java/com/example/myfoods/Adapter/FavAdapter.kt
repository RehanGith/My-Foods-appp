package com.example.myfoods.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoods.Model.MealDB
import com.example.myfoods.databinding.MealCardBinding

class FavAdapter(private val listener: OnFavClickListener) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    class ViewHolder(binding: MealCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val mealName = binding.tvMealName
        val mealImage = binding.imgMeal
    }

    interface OnFavClickListener {
        fun onFavClick(meal: MealDB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    private val differCallBack = object : DiffUtil.ItemCallback<MealDB>() {
        override fun areItemsTheSame(oldItem: MealDB, newItem: MealDB): Boolean {
            return oldItem.mealId == newItem.mealId
        }

        override fun areContentsTheSame(oldItem: MealDB, newItem: MealDB): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mealName.text = differ.currentList[position].mealName
        Glide.with(holder.itemView).load(differ.currentList[position].mealThumb).into(holder.mealImage)
        holder.itemView.setOnClickListener {
            listener.onFavClick(differ.currentList[position])
        }

    }
}