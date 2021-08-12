package com.example.twininterview.android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twininterview.android.adapter.viewholder.DefaultHolder
import com.example.twininterview.android.data.domain.food.model.Food
import com.example.twininterview.android.databinding.ListItemFoodHomeBinding
import com.example.twininterview.android.extensions.toggleView
import com.example.twininterview.android.extensions.loadImage

internal class FoodAdapter(
    var foodItems: List<Food>
) : RecyclerView.Adapter<DefaultHolder<ListItemFoodHomeBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DefaultHolder<ListItemFoodHomeBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return DefaultHolder(ListItemFoodHomeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: DefaultHolder<ListItemFoodHomeBinding>, position: Int) {
        holder.binding.apply {
            model = foodItems[position]
            toggleSelected = toggleView { isSelected = !it }
            isSelected = false
        }
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    fun update(foodItems: List<Food>) {
        this.foodItems = foodItems
        notifyDataSetChanged()
    }
}
