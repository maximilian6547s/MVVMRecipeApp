package com.maximcuker.mvvmrecipeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    cardHeight: Dp,
    xShimmer: Float,
    yShimmer: Float,
    padding: Dp,
    gradientWidth: Float,
) {
    val brush = linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Column(
        modifier = Modifier.padding(padding)
    ) {
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(cardHeight)
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(cardHeight / 10)
                    .background(brush = brush)
            )
        }
    }
}