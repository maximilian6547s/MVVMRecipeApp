package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.findNavController
import com.maximcuker.mvvmrecipeapp.R
import com.maximcuker.mvvmrecipeapp.presentation.components.RecipeList
import com.maximcuker.mvvmrecipeapp.presentation.components.SearchAppBar
import com.maximcuker.mvvmrecipeapp.presentation.theme.AppTheme
import com.maximcuker.mvvmrecipeapp.util.TAG
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    viewModel: RecipeListViewModel,
) {

    Log.d(TAG, "RecipeListScreen: ${viewModel}")

    val recipes = viewModel.recipes.value

    val query = viewModel.query.value

    val selectedCategory = viewModel.selectedCategory.value

    val loading = viewModel.loading.value

    val page = viewModel.page.value

    val dialogQueue = viewModel.dialogQueue

    val scaffoldState = rememberScaffoldState()

    AppTheme(
        displayProgressBar = loading,
        isNetworkAvailable = isNetworkAvailable,
        scaffoldState = scaffoldState,
        darkTheme = isDarkTheme,
        dialogQueue = dialogQueue.queue.value
    ) {

        Scaffold(
            topBar = {
                SearchAppBar(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteSearch = { viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent) },
                    categories = getAllFoodCategories(),
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                    onToggleTheme = { onToggleTheme() }
                )
            },
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            },

            ) {
            RecipeList(
                loading = loading,
                recipes = recipes,
                onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                page = page,
                onTriggerNextPage = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent) },
                onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
            )
        }

    }
}