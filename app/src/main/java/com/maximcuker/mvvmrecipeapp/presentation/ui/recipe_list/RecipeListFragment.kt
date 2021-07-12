package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.maximcuker.mvvmrecipeapp.R
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import com.maximcuker.mvvmrecipeapp.presentation.BaseApplication
import com.maximcuker.mvvmrecipeapp.presentation.components.*
import com.maximcuker.mvvmrecipeapp.presentation.components.HeartAnimationDefinition.HeartButtonState.*
import com.maximcuker.mvvmrecipeapp.presentation.theme.AppTheme
import com.maximcuker.mvvmrecipeapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application:BaseApplication

    private val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(
                    darkTheme = application.isDark.value
                ) {
                    val recipes = viewModel.recipes.value

                    val query = viewModel.query.value

                    val selectedCategory = viewModel.selectedCategory.value

                    val loading = viewModel.loading.value

                    Column {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            scrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangedCategoryScrollPosition = viewModel::onChangedCategoryScrollPosition,
                            onToggleTheme = {
                                application.toggleLightTheme()
                            }
                        )
//                    LoadingRecipeListShimmer(imageHeight = 250.dp)
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            if (loading) {
                                LoadingRecipeListShimmer(imageHeight = 250.dp)
                            } else {
                                LazyColumn {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = { /*TODO*/ })
                                    }
                                }
                            }

                            CircularIndeterminateProgressBar(isDisplayed = loading)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientDemo() {
    val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Blue
    )
    val brush = linearGradient(
        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f)
    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(modifier = Modifier.fillMaxSize().background(brush = brush))

    }

}