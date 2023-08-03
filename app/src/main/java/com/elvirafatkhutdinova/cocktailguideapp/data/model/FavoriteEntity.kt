package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.domain.Favorite

@Entity(
    tableName = "favorite", foreignKeys = [ForeignKey(
        entity = CocktailEntity::class,
        parentColumns = ["id_cocktail"],
        childColumns = ["id_favorite"]
    )]
)
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "id_favorite")
    val idFavorite: String
)

fun FavoriteEntity.asDomainModel(): Favorite {
    return Favorite(
        id = this.id,
        idFavorite = this.idFavorite
    )
}