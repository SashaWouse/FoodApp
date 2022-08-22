package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.CategoryMeals
import com.example.foodapp.databinding.PopularItemsBinding

class PopularAdapter(): RecyclerView.Adapter<PopularAdapter.PopularMealViewHolder>() {
    lateinit var onItemClick:((CategoryMeals) -> Unit)
    private var mealList = ArrayList<CategoryMeals>()

    fun setMeals(mealsList:ArrayList<CategoryMeals>){
        // assign the mealList
        this.mealList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgPopularItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class PopularMealViewHolder(var binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root)
}