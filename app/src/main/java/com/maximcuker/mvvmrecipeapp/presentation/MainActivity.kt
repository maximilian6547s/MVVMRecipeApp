package com.maximcuker.mvvmrecipeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maximcuker.mvvmrecipeapp.R
import com.maximcuker.mvvmrecipeapp.presentation.navigation.Screen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeDetailScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeViewModel
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //entry point also must be in an activity(host)
class MainActivity : AppCompatActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.RecipeList.route
            ) {
                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(
                        AmbientContext.current,
                        navBackStackEntry
                    )
                    //viewModel - is part of compose.ui dependency
                    val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
                    RecipeListScreen(
                        isDarkTheme = (application as BaseApplication).isDark.value,
                        onToggleTheme = (application as BaseApplication)::toggleLightTheme,
                        viewModel = viewModel
                    )
                }

                composable(
                    route = Screen.RecipeDetail.route
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(
                        AmbientContext.current,
                        navBackStackEntry
                    )
                    val viewModel: RecipeViewModel = viewModel("RecipeViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = (application as BaseApplication).isDark.value,
                        recipeId = 1, //hardcoded fo now cause we dont have a mechanism for passing arguments to composables
                        viewModel = viewModel
                    )

                }
            }
        }
    }
}