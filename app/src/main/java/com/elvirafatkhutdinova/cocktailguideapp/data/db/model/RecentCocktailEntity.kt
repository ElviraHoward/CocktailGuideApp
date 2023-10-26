package com.elvirafatkhutdinova.cocktailguideapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_cocktail")
data class RecentCocktailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_recent_cocktail")
    val idRecent: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)