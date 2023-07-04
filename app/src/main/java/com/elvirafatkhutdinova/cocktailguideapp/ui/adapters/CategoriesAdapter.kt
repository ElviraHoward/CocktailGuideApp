package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvirafatkhutdinova.cocktailguideapp.databinding.CategoryItemBinding
import java.util.Locale.Category

class CategoriesAdapter(private val categories : List<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemBinding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoriesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
       return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        with(holder) {
            val category = categories[position]
            itemBinding.categoryName.text = category.name
        }
    }

    inner class CategoriesViewHolder(val itemBinding: CategoryItemBinding) : RecyclerView.ViewHolder(itemBinding.root)
}