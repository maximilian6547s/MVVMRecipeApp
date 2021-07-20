package com.maximcuker.mvvmrecipeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.maximcuker.mvvmrecipeapp.datastore.SettingsDataStore
import com.maximcuker.mvvmrecipeapp.presentation.navigation.Screen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeDetailScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeDetailViewModel
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.maximcuker.mvvmrecipeapp.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint //entry point also must be in an activity(host)
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unRegisterConnectionObserver(this)
    }

    @ExperimentalComposeUiApi
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
                        LocalContext.current,
                        navBackStackEntry
                    )
                    //viewModel - is part of compose.ui dependency
                    val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
                    RecipeListScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        onToggleTheme = settingsDataStore::toggleTheme,
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        viewModel = viewModel
                    )
                }

                composable(
                    route = Screen.RecipeDetail.route + "/{recipeId}",
                    arguments = listOf(navArgument("recipeId") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(
                        LocalContext.current,
                        navBackStackEntry
                    )
                    val viewModel: RecipeDetailViewModel = viewModel("RecipeViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}