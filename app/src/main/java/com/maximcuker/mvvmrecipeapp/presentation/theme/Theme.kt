package com.maximcuker.mvvmrecipeapp.presentation.theme

import android.app.Dialog
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
import com.maximcuker.mvvmrecipeapp.presentation.components.*

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
//            Dialog example
            val isShowing = remember { mutableStateOf(true) }
            if (isShowing.value) {
                val dialogInfo = GenericDialogInfo.Builder()
                    .title("Error")
                    .onDismiss { isShowing.value = false }
                    .description("Hey look a dialog description")
                    .positiveAction(PositiveAction(
                        positiveBtnTxt = "Ok",
                        onPositiveAction = { isShowing.value = false }
                    ))
                    .negativeAction(NegativeAction(
                            negativeBtnTxt = "Cancel",
                            onNegativeAction = { isShowing.value = false }
                    ))
                    .build()

                GenericDialog(
                    onDismiss = dialogInfo.onDismiss,
                    title = dialogInfo.title,
                    description = dialogInfo.description,
                    positiveAction = dialogInfo.positiveAction,
                    negativeAction = dialogInfo.negativeAction
                )
            }
        }
    }
}