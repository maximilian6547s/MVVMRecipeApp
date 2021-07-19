package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.mutableStateListOf
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.FoodCategory.*
import java.util.*

enum class FoodCategory(val value: String) {
    ERROR("error"),
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut"),
}

fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(
        ERROR, CHICKEN,
        BEEF, SOUP,
        DESSERT, VEGETARIAN, MILK,
        VEGAN, PIZZA, DONUT
    )
}

fun getFoodCategory(value: String): FoodCategory? {
    val map = values().associateBy(FoodCategory::value)
    return map[value]
}