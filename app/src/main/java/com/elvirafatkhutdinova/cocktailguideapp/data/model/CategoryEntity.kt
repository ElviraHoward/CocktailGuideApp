package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elvirafatkhutdinova.cocktailguideapp.domain.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_category") val strCategory: String
)

fun List<CategoryEntity>.asDomainModel(): List<Category> {
    return map { it.asDomainModel() }
}

fun CategoryEntity.asDomainModel() : Category {
    return Category(strCategory = this.strCategory)
}