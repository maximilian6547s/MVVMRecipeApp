package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maximcuker.mvvmrecipeapp.presentation.components.IMAGE_HEIGHT
import com.maximcuker.mvvmrecipeapp.presentation.components.LoadingRecipeShimmer
import com.maximcuker.mvvmrecipeapp.presentation.components.RecipeView
import com.maximcuker.mvvmrecipeapp.presentation.theme.AppTheme
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.maximcuker.mvvmrecipeapp.util.TAG

@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    recipeId:  Int?,
    viewModel: RecipeViewModel,

    ) {

    Log.d(TAG, "RecipeDetailScreen : ${viewModel}")
    Text(text = "Recipe detail screen: ${recipeId}")
//    val loading = viewModel.loading.value
//
//    val recipe = viewModel.recipe.value
//
//    val scaffoldState = rememberScaffoldState()
//    AppTheme(
//        darkTheme = isDarkTheme,
//        displayProgressBar = loading,
//        scaffoldState = scaffoldState
//    ) {
//        Scaffold(
//            scaffoldState = scaffoldState,
//            snackbarHost = {
//                scaffoldState.snackbarHostState
//            }
//        ) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                if (loading && recipe == null) {
//                    LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
//                } else {
//                    recipe?.let {
//                        if (it.id == 1) {
//                            snackbarController.showSnackbar(
//                                scaffoldState = scaffoldState,
//                                message = "An error occurred with this recipe.",
//                                actionLabel = "OK"
//                            )
//                        } else {
//                            RecipeView(recipe = it)
//                        }
//                    }
//                }
//            }
//        }
//    }
}