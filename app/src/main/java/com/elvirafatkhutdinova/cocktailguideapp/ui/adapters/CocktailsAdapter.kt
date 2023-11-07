package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.CocktailItemBinding
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.ui.OnItemClickListener

class CocktailsAdapter() : ListAdapter<CocktailsAndFavorites, CocktailsAdapter.CocktailsViewHolder>(CocktailDiffUtil),
    OnItemClickListener<(String) -> Unit> {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val itemBinding = CocktailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CocktailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
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

    inner class CocktailsViewHolder(val binding: CocktailItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onItemClick(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    private object CocktailDiffUtil : DiffUtil.ItemCallback<CocktailsAndFavorites>() {
        override fun areItemsTheSame(oldItem: CocktailsAndFavorites, newItem: CocktailsAndFavorites): Boolean {
            return oldItem.cocktail.idDrink == newItem.cocktail.idDrink
        }

        override fun areContentsTheSame(oldItem: CocktailsAndFavorites, newItem: CocktailsAndFavorites): Boolean {
            return oldItem == newItem
        }

    }
}