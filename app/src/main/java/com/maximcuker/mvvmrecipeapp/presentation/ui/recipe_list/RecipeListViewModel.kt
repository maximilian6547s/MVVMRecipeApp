package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maximcuker.mvvmrecipeapp.domain.model.Recipe
import com.maximcuker.mvvmrecipeapp.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Named

class RecipeListViewModel
@ViewModelInject
constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String

) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    //need fo tracking position of category
    var categoryScrollPosition: Float = 0f

    val loading = mutableStateOf(false)

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(1000)

            val result = repository.search(
                token, 1, query = query.value
            )

            recipes.value = result

            loading.value = false
        }
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangedCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }
}