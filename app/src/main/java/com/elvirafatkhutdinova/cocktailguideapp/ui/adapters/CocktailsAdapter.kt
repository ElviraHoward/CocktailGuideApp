package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.CocktailItemBinding
import com.elvirafatkhutdinova.cocktailguideapp.domain.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.ui.OnItemClickListener

class CocktailsAdapter(private val cocktailsAndFavorites: List<CocktailsAndFavorites>) :
    RecyclerView.Adapter<CocktailsAdapter.CocktailsViewHolder>(),
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

    override fun getItemCount(): Int {
        return cocktailsAndFavorites.size
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        with(holder) {
            val cocktail = cocktailsAndFavorites[position].cocktail
            val favorite = cocktailsAndFavorites[position].favorite
            holder.itemView.apply {
                binding.textCocktail.text = cocktail.strDrink
                Glide.with(this).load(cocktail.strDrinkThumb).into(binding.imgCocktail)
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

    inner class CocktailsViewHolder(val binding: CocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onItemClick(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}