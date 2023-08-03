package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.elvirafatkhutdinova.cocktailguideapp.domain.CocktailsAndFavorites

data class CocktailsAndFavoritesEntity(

    @Embedded val cocktailEntity: CocktailEntity,
    @Relation(parentColumn = "id_cocktail", entityColumn = "id_favorite")
    val favoriteEntity: FavoriteEntity?
)

fun List<CocktailsAndFavoritesEntity>.asDomainModel(): List<CocktailsAndFavorites> {
    return map { it.asDomainModel()}
}

fun CocktailsAndFavoritesEntity.asDomainModel() : CocktailsAndFavorites {
    return CocktailsAndFavorites(
        cocktail = this.cocktailEntity.asDomainModel(),
        favorite = this.favoriteEntity?.asDomainModel()
    )
}