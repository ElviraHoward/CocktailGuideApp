package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.databinding.CocktailItemBinding
import com.elvirafatkhutdinova.cocktailguideapp.model.Drink

class CocktailsAdapter: RecyclerView.Adapter<CocktailsAdapter.CocktailsViewHolder>() {

    private var cocktailsList = emptyList<Drink>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val itemBinding = CocktailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CocktailsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        with(holder) {
            val cocktail = cocktailsList[position]
            holder.itemView.apply {
                binding.cocktailText.text = cocktail.strDrink
                Glide.with(this).load(cocktail.strDrinkThumb).into(binding.cocktailImage)
            }
        }
    }

    fun setData (newCocktailsList : List<Drink>) {
        cocktailsList = newCocktailsList
        notifyDataSetChanged()
    }

    inner class CocktailsViewHolder(val binding: CocktailItemBinding) : RecyclerView.ViewHolder(binding.root)
}