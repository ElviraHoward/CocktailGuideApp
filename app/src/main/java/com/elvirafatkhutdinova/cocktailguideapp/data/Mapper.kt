package com.elvirafatkhutdinova.cocktailguideapp.data

import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.*
import com.elvirafatkhutdinova.cocktailguideapp.data.network.model.CategoryListResponse
import com.elvirafatkhutdinova.cocktailguideapp.data.network.model.CocktailListResponse
import com.elvirafatkhutdinova.cocktailguideapp.domain.Category
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.domain.Favorite

fun CocktailEntity.asDomainModel() = Cocktail(
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

fun CocktailsAndFavoritesEntity.asDomainModel() = CocktailsAndFavorites(
    cocktail = this.cocktailEntity.asDomainModel(),
    favorite = this.favoriteEntity?.asDomainModel()
)

fun CategoryEntity.asDomainModel() = Category(strCategory = this.strCategory)

fun FavoriteEntity.asDomainModel() = Favorite(
    id = this.id,
    idFavorite = this.idFavorite
)

fun CategoryListResponse.asDatabaseModel() =
    drinks.map { CategoryEntity(strCategory = it.strCategory) }

fun CocktailListResponse.asDomainModel() = drinks.map {
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
        strMeasure15 = it.strMeasure15
    )
}

fun CocktailListResponse.asDatabaseModel() = drinks.map {
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
        strMeasure15 = it.strMeasure15
    )
}