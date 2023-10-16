package com.elvirafatkhutdinova.cocktailguideapp.domain.model

data class CocktailsAndFavorites(
    val cocktail: Cocktail,
    val favorite: Favorite?
)