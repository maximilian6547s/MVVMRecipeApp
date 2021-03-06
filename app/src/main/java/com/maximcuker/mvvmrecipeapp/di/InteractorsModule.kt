package com.maximcuker.mvvmrecipeapp.di

import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntityMapper
import com.maximcuker.mvvmrecipeapp.interactors.recipe.GetRecipe
import com.maximcuker.mvvmrecipeapp.interactors.recipe_list.RestoreRecipes
import com.maximcuker.mvvmrecipeapp.interactors.recipe_list.SearchRecipes
import com.maximcuker.mvvmrecipeapp.network.RecipeService
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) //we using viewmodelComponent because Use cases only need exist while view model injected in to exist
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper
    ):SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeDao = recipeDao,
            dtoMapper = recipeDtoMapper,
            entityMapper = recipeEntityMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideRestoreRecipes(
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
    ):RestoreRecipes {
        return RestoreRecipes(
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper
    ):GetRecipe {
        return GetRecipe(
            recipeService = recipeService,
            recipeDao = recipeDao,
            recipeDtoMapper = recipeDtoMapper,
            entityMapper = recipeEntityMapper
        )
    }

}