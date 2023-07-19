package com.elvirafatkhutdinova.cocktailguideapp.network.model

import com.elvirafatkhutdinova.cocktailguideapp.data.model.CocktailEntity
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail

data class CocktailListResponse(
    val drinks: List<CocktailResponse>
)

fun CocktailListResponse.asDomainModel(): List<Cocktail> {
    return drinks.map {
        Cocktail(
            idDrink = it.idDrink,
            strAlcoholic = it.strAlcoholic,
            strCategory = it.strCategory,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb,
            strGlass = it.strGlass,
            strInstructions = it.strInstructions,
            strIngredient1 = it.strIngredient1,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11 = it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            false
        )
    }
}

fun CocktailListResponse.asDatabaseModel(): List<CocktailEntity> {
    return drinks.map {
        CocktailEntity(
            idDrink = it.idDrink,
            strAlcoholic = it.strAlcoholic,
            strCategory = it.strCategory,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb,
            strGlass = it.strGlass,
            strInstructions = it.strInstructions,
            strIngredient1 = it.strIngredient1,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11 = it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            false
        )
    }
}