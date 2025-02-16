package com.example.myfoods.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoods.Model.Meal
import com.example.myfoods.R
import com.example.myfoods.databinding.SingleMealCardBinding

class MealAdapter(private val listener: OnItemViewClick): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(binding :SingleMealCardBinding ) : RecyclerView.ViewHolder(binding.root) {
        val mealName = binding.tvMealName
        val mealImage = binding.imgMeal
    }

    interface OnItemViewClick {
        fun onItemClick(meal: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(SingleMealCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    private val differCallBack =object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.strSource == newItem.strSource
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = differ.currentList[position]
        holder.mealName.text = meal.strMeal ?: "Unknown"
        holder.itemView.apply {
            Glide.with(this).load(meal.strMealThumb ?: R.drawable.ic_launcher_foreground)
                .into(holder.mealImage)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(meal)
        }

    }

}