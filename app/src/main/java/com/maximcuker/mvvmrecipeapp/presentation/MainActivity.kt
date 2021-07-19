package com.maximcuker.mvvmrecipeapp.presentation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.maximcuker.mvvmrecipeapp.presentation.navigation.Screen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeDetailScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe.RecipeDetailViewModel
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //entry point also must be in an activity(host)
class MainActivity : AppCompatActivity() {

    val TAG = "c-Manager"
    lateinit var cm: ConnectivityManager

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG, "onAvailable: ${network}")
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG, "onLost: ${network}")
        }
    }

    override fun onStart() {
        super.onStart()
        cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        cm.unregisterNetworkCallback(networkCallback)
    }

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
                        AmbientContext.current,
                        navBackStackEntry
                    )
                    val viewModel: RecipeDetailViewModel = viewModel("RecipeViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = (application as BaseApplication).isDark.value,
                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                        viewModel = viewModel
                    )

                }
            }
        }
    }
}