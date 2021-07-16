package com.maximcuker.mvvmrecipeapp.interactors.recipe

import android.util.Log
import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntityMapper
import com.maximcuker.mvvmrecipeapp.domain.data.DataState
import com.maximcuker.mvvmrecipeapp.domain.model.Recipe
import com.maximcuker.mvvmrecipeapp.network.RecipeService
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import com.maximcuker.mvvmrecipeapp.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetRecipe(
    private val recipeDao: RecipeDao,
    private val recipeService: RecipeService,
    private val entityMapper: RecipeEntityMapper,
    private val recipeDtoMapper: RecipeDtoMapper
) {

    fun execute(recipeId: Int, token: String): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading<Recipe>())

            delay(1000)

            var recipe = getRecipeFromCache(recipeId = recipeId)

            if (recipe != null) {
                emit(DataState.success(recipe))
            } else {
                val networkRecipe = getRecipeFromNetwork(token, recipeId)
                recipeDao.insertRecipe(entityMapper.mapFromDomainModel(networkRecipe))
            }

            recipe = getRecipeFromCache(recipeId)

            if (recipe != null) {
                emit(DataState.success(recipe))
            } else {
                throw Exception("Unable to get recipe from the cache.")
            }

        } catch (e: Exception) {
            Log.e(TAG, "execute: ${e.message}")
            emit(DataState.error<Recipe>(e.message ?: "Unknown error"))
        }
    }

    private suspend fun getRecipeFromCache(recipeId: Int): Recipe? {
        return recipeDao.getRecipeById(recipeId)?.let { recipeEntity ->
            entityMapper.mapToDomainModel(recipeEntity)
        }
    }

    private suspend fun getRecipeFromNetwork(token: String, recipeId: Int): Recipe {
        return recipeDtoMapper.mapToDomainModel(recipeService.get(token = token, id = recipeId))
    }
}

