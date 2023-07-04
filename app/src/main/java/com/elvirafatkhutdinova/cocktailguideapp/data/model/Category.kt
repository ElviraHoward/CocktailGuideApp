package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "id_category") val strCategory: String
)