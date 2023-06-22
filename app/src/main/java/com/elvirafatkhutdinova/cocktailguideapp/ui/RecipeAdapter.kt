package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Drink
import com.elvirafatkhutdinova.cocktailguideapp.data.model.RecipeTuple
import com.elvirafatkhutdinova.cocktailguideapp.databinding.IngredientItemBinding

class RecipeAdapter(private val recipeList: List<Pair<String?, String?>>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = IngredientItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        with(holder) {
            val recipe = recipeList[position]
            holder.itemView.apply {
                binding.ingredientName.text = recipe.first
                binding.measureName.text = recipe.second
            }
        }
    }

    inner class RecipeViewHolder(val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root)
}