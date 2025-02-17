package com.example.myfoods.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoods.Model.MealX
import com.example.myfoods.R
import com.example.myfoods.databinding.MostPopularCardBinding
import com.example.myfoods.databinding.SingleMealCardBinding

class MealAdapter(
    private val listener: OnItemViewClick,
    private val adapterType: Int // NEW: adapter type to control the layout
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    companion object {
        const val MOST_POPULAR_VIEW_TYPE = 1
        const val SINGLE_MEAL_VIEW_TYPE = 2
    }

    interface OnItemViewClick {
        fun onItemClick(meal: MealX)
    }

    sealed class MealViewBinding {
        data class MostPopularCard(val binding: MostPopularCardBinding) : MealViewBinding()
        data class SingleMealCard(val binding: SingleMealCardBinding) : MealViewBinding()
    }

    class MealViewHolder(private val binding: MealViewBinding) : RecyclerView.ViewHolder(
        when (binding) {
            is MealViewBinding.MostPopularCard -> binding.binding.root
            is MealViewBinding.SingleMealCard -> binding.binding.root
        }
    ) {
        val mealImage: ImageView? = when (binding) {
            is MealViewBinding.MostPopularCard -> binding.binding.imgPopularMeal
            is MealViewBinding.SingleMealCard -> binding.binding.imgMeal
        }
        val mealText: TextView? = when (binding) {
            is MealViewBinding.SingleMealCard -> binding.binding.tvMealName
            else -> null
        }
    }

    // Instead of checking a flag in the data, we simply return the adapterType provided in the constructor.
    override fun getItemViewType(position: Int): Int {
        return adapterType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return when (viewType) {
            MOST_POPULAR_VIEW_TYPE -> {
                val binding = MostPopularCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MealViewHolder(MealViewBinding.MostPopularCard(binding))
            }
            SINGLE_MEAL_VIEW_TYPE -> {
                val binding = SingleMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MealViewHolder(MealViewBinding.SingleMealCard(binding))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<MealX>() {
        override fun areItemsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = differ.currentList[position]
        holder.itemView.apply {
            holder.mealImage?.let { imageView ->
                Glide.with(this)
                    .load(meal.strMealThumb.ifEmpty { R.drawable.ic_launcher_foreground })
                    .into(imageView)
            }
        }
        // For the single meal card, set the text
        holder.mealText?.text = meal.strMeal

        holder.itemView.setOnClickListener {
            listener.onItemClick(meal)
        }
    }
}

