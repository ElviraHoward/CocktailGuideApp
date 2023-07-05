package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Category
import com.elvirafatkhutdinova.cocktailguideapp.databinding.CategoryItemBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.OnItemClickListener

class CategoriesAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(), OnItemClickListener<(String) -> Unit> {

    private var onItemClickListener : ((String) -> Unit)? = null

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
            holder.itemView.apply {
                itemBinding.categoryName.text = category.strCategory
                setOnClickListener {
                    onItemClickListener?.let { it(category.strCategory)}
                }
            }
        }
    }

    inner class CategoriesViewHolder(val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onItemClick(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}