package com.maximcuker.mvvmrecipeapp.interactors.recipe_list

import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntityMapper
import com.maximcuker.mvvmrecipeapp.domain.data.DataState
import com.maximcuker.mvvmrecipeapp.domain.model.Recipe
import com.maximcuker.mvvmrecipeapp.network.RecipeService
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import com.maximcuker.mvvmrecipeapp.util.RECIPE_PAGINATION_PAGE_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeDao: RecipeDao,
    private val recipeService: RecipeService,
    private val entityMapper: RecipeEntityMapper,
    private val dtoMapper: RecipeDtoMapper,
) {
    fun execute(
        token: String,
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading<List<Recipe>>())

            //just to show pagnation/progressbar
            delay(1000)

            //force error for testing
            //TODO("Delete in future")
            if (query == "error") {
                throw Exception("Search failed")
            }

            //TODO("Check if there is an internet connection")
            val recipes = getRecipesFromNetwork(token, page, query)

            //insert to the cache
            recipeDao.insertRecipes(entityMapper.toEntityList(recipes))

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeDao.getAllRecipes(
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            } else {
                recipeDao.searchRecipes(
                    query = query,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }

            // emit List<Recipe> from a cache
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))

        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown error"))
        }
    }

    //This can throw an exception if there is no network connection
    private suspend fun getRecipesFromNetwork(
        token: String,
        page: Int,
        query: String
    ): List<Recipe> {
        return dtoMapper.toDomainList(
            recipeService.search(
                token, page, query
            ).recipes
        )
    }


}