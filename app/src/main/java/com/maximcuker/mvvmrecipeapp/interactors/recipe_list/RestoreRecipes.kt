package com.maximcuker.mvvmrecipeapp.interactors.recipe_list

import android.util.Log
import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntityMapper
import com.maximcuker.mvvmrecipeapp.domain.data.DataState
import com.maximcuker.mvvmrecipeapp.domain.model.Recipe
import com.maximcuker.mvvmrecipeapp.util.RECIPE_PAGINATION_PAGE_SIZE
import com.maximcuker.mvvmrecipeapp.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RestoreRecipes (
    private val recipeDao: RecipeDao,
    private val entityMapper: RecipeEntityMapper,
) {

    fun execute(
        page:Int,
        query:String,
    ):Flow<DataState<List<Recipe>>> = flow {
        try {

            emit(DataState.loading())

            delay(1000)

            val cache = if (query.isBlank()) {
                recipeDao.restoreAllRecipes(
                    page = page,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                )
            }else{
                recipeDao.restoreRecipes(
                    query = query,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }

            val list = entityMapper.fromEntityList(cache)
            emit(DataState.success(list))

        } catch (e:Exception) {
            Log.e(TAG, "execute: ${e.message}")
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown error"))
        }
    }
}