package com.example.myfoods.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoods.databinding.BottomSheetDialogBinding
import com.example.myfoods.databinding.SingleMealCardBinding
import com.example.myfoods.model.Meal

class SearchAdapter(private val listener: onSearchItemClick): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(binding: BottomSheetDialogBinding) : RecyclerView.ViewHolder(binding.root) {
        val mealName = binding.tvMealNameInBtmsheet
        val mealImage = binding.imgCategory
        val mealArea = binding.tvMealCountry
        val mealCategory = binding.tvMealCategory
        val btnReadMore = binding.tvReadMoreBtnsheet
    }

    interface onSearchItemClick {
        fun onItemClick(meal: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(BottomSheetDialogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    val differCallBack = object :  DiffUtil.ItemCallback<Meal> () {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.mealName.text = differ.currentList[position].strMeal
        Glide.with(holder.itemView).load(differ.currentList[position].strMealThumb).into(holder.mealImage)
        holder.mealArea.text = differ.currentList[position].strArea
        holder.mealCategory.text = differ.currentList[position].strCategory
        holder.btnReadMore.setOnClickListener {
            listener.onItemClick(differ.currentList[position])
        }
    }
}