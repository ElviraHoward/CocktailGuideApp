package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail

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

fun List<CocktailEntity>.asDomainModel(): List<Cocktail> {
    return map { it.asDomainModel()}
}

fun CocktailEntity.asDomainModel(): Cocktail {
    return Cocktail(
        idDrink = this.idDrink,
        strAlcoholic = this.strAlcoholic,
        strCategory = this.strCategory,
        strDrink = this.strDrink,
        strDrinkThumb = this.strDrinkThumb,
        strGlass = this.strGlass,
        strInstructions = this.strInstructions,
        strIngredient1 = this.strIngredient1,
        strIngredient2 = this.strIngredient2,
        strIngredient3 = this.strIngredient3,
        strIngredient4 = this.strIngredient4,
        strIngredient5 = this.strIngredient5,
        strIngredient6 = this.strIngredient6,
        strIngredient7 = this.strIngredient7,
        strIngredient8 = this.strIngredient8,
        strIngredient9 = this.strIngredient9,
        strIngredient10 = this.strIngredient10,
        strIngredient11 = this.strIngredient11,
        strIngredient12 = this.strIngredient12,
        strIngredient13 = this.strIngredient13,
        strIngredient14 = this.strIngredient14,
        strIngredient15 = this.strIngredient15,
        strMeasure1 = this.strMeasure1,
        strMeasure2 = this.strMeasure2,
        strMeasure3 = this.strMeasure3,
        strMeasure4 = this.strMeasure4,
        strMeasure5 = this.strMeasure5,
        strMeasure6 = this.strMeasure6,
        strMeasure7 = this.strMeasure7,
        strMeasure8 = this.strMeasure8,
        strMeasure9 = this.strMeasure9,
        strMeasure10 = this.strMeasure10,
        strMeasure11 = this.strMeasure11,
        strMeasure12 = this.strMeasure12,
        strMeasure13 = this.strMeasure13,
        strMeasure14 = this.strMeasure14,
        strMeasure15 = this.strMeasure15
    )
}