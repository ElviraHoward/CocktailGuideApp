package com.elvirafatkhutdinova.cocktailguideapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_cocktail") val idDrink: String,
    @ColumnInfo(name = "type") val strAlcoholic: String,
    @ColumnInfo(name = "category") val strCategory: String,
    @ColumnInfo(name = "name") val strDrink: String,
    @ColumnInfo(name = "image_thumbnail") val strDrinkThumb: String,
    @ColumnInfo(name = "glass") val strGlass: String,
    @ColumnInfo(name = "instructions") val strInstructions: String,
    @ColumnInfo(name = "ingredient_1") val strIngredient1: String?,
    @ColumnInfo(name = "ingredient_2") val strIngredient2: String?,
    @ColumnInfo(name = "ingredient_3") val strIngredient3: String?,
    @ColumnInfo(name = "ingredient_4") val strIngredient4: String?,
    @ColumnInfo(name = "ingredient_5") val strIngredient5: String?,
    @ColumnInfo(name = "ingredient_6") val strIngredient6: String?,
    @ColumnInfo(name = "ingredient_7") val strIngredient7: String?,
    @ColumnInfo(name = "ingredient_8") val strIngredient8: String?,
    @ColumnInfo(name = "ingredient_9") val strIngredient9: String?,
    @ColumnInfo(name = "ingredient_10") val strIngredient10: String?,
    @ColumnInfo(name = "ingredient_11") val strIngredient11: String?,
    @ColumnInfo(name = "ingredient_12") val strIngredient12: String?,
    @ColumnInfo(name = "ingredient_13") val strIngredient13: String?,
    @ColumnInfo(name = "ingredient_14") val strIngredient14: String?,
    @ColumnInfo(name = "ingredient_15") val strIngredient15: String?,
    @ColumnInfo(name = "measure_1") val strMeasure1: String?,
    @ColumnInfo(name = "measure_2") val strMeasure2: String?,
    @ColumnInfo(name = "measure_3") val strMeasure3: String?,
    @ColumnInfo(name = "measure_4") val strMeasure4: String?,
    @ColumnInfo(name = "measure_5") val strMeasure5: String?,
    @ColumnInfo(name = "measure_6") val strMeasure6: String?,
    @ColumnInfo(name = "measure_7") val strMeasure7: String?,
    @ColumnInfo(name = "measure_8") val strMeasure8: String?,
    @ColumnInfo(name = "measure_9") val strMeasure9: String?,
    @ColumnInfo(name = "measure_10") val strMeasure10: String?,
    @ColumnInfo(name = "measure_11") val strMeasure11: String?,
    @ColumnInfo(name = "measure_12") val strMeasure12: String?,
    @ColumnInfo(name = "measure_13") val strMeasure13: String?,
    @ColumnInfo(name = "measure_14") val strMeasure14: String?,
    @ColumnInfo(name = "measure_15") val strMeasure15: String?
)