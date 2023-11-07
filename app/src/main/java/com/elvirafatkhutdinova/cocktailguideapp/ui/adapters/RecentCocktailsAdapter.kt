package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.RecentCocktailItemBinding
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail
import com.elvirafatkhutdinova.cocktailguideapp.ui.OnItemClickListener

class RecentCocktailsAdapter() : ListAdapter<CocktailsAndFavorites, RecentCocktailsAdapter.RecentCocktailViewHolder>(RecentCocktailDiffUtil),
    OnItemClickListener<(String) -> Unit> {

    private var onItemClickListener: ((String) -> Unit)? = null

    inner class RecentCocktailViewHolder(val binding: RecentCocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentCocktailViewHolder {
        val itemBinding = RecentCocktailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentCocktailViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecentCocktailViewHolder, position: Int) {
        with(holder) {
            val cocktail = getItem(position).cocktail
            val favorite = getItem(position).favorite
            holder.itemView.apply {
                binding.textCocktail.text = cocktail.strDrink
                Glide.with(this).load(cocktail.strDrinkThumb).placeholder(R.drawable.ic_empty_image)
                    .into(binding.imgCocktail)
                setOnClickListener {
                    onItemClickListener?.let { it(cocktail.idDrink) }
                }
                binding.imgFavorite.setImageResource(
                    if (!favorite?.idFavorite.isNullOrEmpty())
                        R.drawable.ic_added_favorite
                    else
                        R.drawable.ic_add_to_favorites
                )
            }
        }
    }

    override fun onItemClick(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    private object RecentCocktailDiffUtil : DiffUtil.ItemCallback<CocktailsAndFavorites>() {
        override fun areItemsTheSame(oldItem: CocktailsAndFavorites, newItem: CocktailsAndFavorites): Boolean {
            return oldItem.cocktail.idDrink == newItem.cocktail.idDrink
        }

        override fun areContentsTheSame(oldItem: CocktailsAndFavorites, newItem: CocktailsAndFavorites): Boolean {
            return oldItem == newItem
        }

    }
}