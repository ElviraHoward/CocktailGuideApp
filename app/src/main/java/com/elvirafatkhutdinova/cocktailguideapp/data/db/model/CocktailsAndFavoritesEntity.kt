package com.elvirafatkhutdinova.cocktailguideapp.data.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class CocktailsAndFavoritesEntity(

    @Embedded val cocktailEntity: CocktailEntity,
    @Relation(parentColumn = "id_cocktail", entityColumn = "id_favorite")
    val favoriteEntity: FavoriteEntity?
)