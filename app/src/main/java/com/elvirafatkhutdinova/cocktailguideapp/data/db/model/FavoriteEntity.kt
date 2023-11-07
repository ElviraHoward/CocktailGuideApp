package com.elvirafatkhutdinova.cocktailguideapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite", foreignKeys = [ForeignKey(
        entity = CocktailEntity::class,
        parentColumns = ["id_cocktail"],
        childColumns = ["id_favorite"]
    )]
)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_favorite")
    val idFavorite: String
)