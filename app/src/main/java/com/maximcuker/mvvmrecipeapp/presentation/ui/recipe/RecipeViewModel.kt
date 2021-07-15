package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maximcuker.mvvmrecipeapp.domain.model.Recipe
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeEvent.*
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListEvent
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.STATE_KEY_SELECTED_CATEGORY
import com.maximcuker.mvvmrecipeapp.repository.RecipeRepository
import com.maximcuker.mvvmrecipeapp.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RECIPE = "state.key.recipe"

@HiltViewModel
class RecipeViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val token: String,
    private val state: SavedStateHandle,
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        // restore if process dies
        state.get<Int>(STATE_KEY_RECIPE)?.let {
            onTriggerEvent(GetRecipeEvent(it))
        }
    }

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception ${e}, ${e.cause}")
            }
        }
    }

    private suspend fun getRecipe(id:Int) {
        loading.value = true

        //simulate delay to show loading
        delay(1000)

        val recipe = recipeRepository.get(token = token, id = id)
        this.recipe.value = recipe

        state.set(STATE_KEY_RECIPE,recipe.id)
        loading.value = false
    }
}