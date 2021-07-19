package com.maximcuker.mvvmrecipeapp.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maximcuker.mvvmrecipeapp.presentation.components.CircularIndeterminateProgressBar
import com.maximcuker.mvvmrecipeapp.presentation.components.DefaultSnackbar

private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

private val DarkThemeColors = darkColors(
    primary = Blue700,
    primaryVariant = Color.White,
    onPrimary = Color.White,
    secondary = Black1,
    onSecondary = Color.White,
    error = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White,
)

@ExperimentalMaterialApi
@Composable
fun AppTheme(
    darkTheme: Boolean,
    displayProgressBar: Boolean,
    scaffoldState: ScaffoldState,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = if (!darkTheme) Grey1 else Color.Black)
        ) {
            content()
            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar)
            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = {
                    scaffoldState.snackbarHostState?.currentSnackbarData?.dismiss()
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            val isShowing = remember { mutableStateOf(true) }
            if (isShowing.value) {
                AlertDialog(
                    onDismissRequest = { isShowing.value = false },
                    title = { Text(text = "Title") },
                    text = { Text(text = "Description") },
                    buttons = {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                modifier = Modifier.padding(8.dp),
                                colors = buttonColors(backgroundColor = MaterialTheme.colors.onError),
                                onClick = { isShowing.value = false }
                            ) {
                                Text(text = "Cancel")
                            }
                            Button(
                                modifier = Modifier.padding(8.dp),
                                onClick = { isShowing.value = false }
                            ) {
                                Text(text = "Ok")
                            }
                        }
                    }
                )
            }
        }
    }
}