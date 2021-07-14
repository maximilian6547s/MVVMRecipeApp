package com.maximcuker.mvvmrecipeapp.presentation.components

import android.util.Log
import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maximcuker.mvvmrecipeapp.presentation.components.util.ShimmerAnimationDefinitions
import com.maximcuker.mvvmrecipeapp.presentation.components.util.ShimmerAnimationDefinitions.AnimationState.*
import com.maximcuker.mvvmrecipeapp.util.TAG
import androidx.compose.ui.graphics.Brush.Companion.linearGradient as Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient as linearGradient

@Composable
fun LoadingRecipeShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    WithConstraints() {

        val cardWidthPx = with(AmbientDensity.current) {
            ((maxWidth - (padding * 2)).toPx())
        }
        val cardHeightPx = with(AmbientDensity.current) {
            ((imageHeight - (padding)).toPx())
        }

        val cardAnimationDefinition = remember {
            ShimmerAnimationDefinitions(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx
            )
        }

        val cardShimmerTranslateAnim = transition(
            definition = cardAnimationDefinition.shimmerTransitionDefinition,
            initState = START,
            toState = END
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.9f),
        )

        val xCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.xShimmerPropKey]
        val yCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.yShimmerPropKey]
        Log.d(TAG, "LoadingRecipeListShimmer: x ${xCardShimmer}")
        Log.d(TAG, "LoadingRecipeListShimmer: y ${yCardShimmer}")

        ScrollableColumn {
            val brush = linearGradient(
                colors,
                start = Offset(
                    xCardShimmer - cardAnimationDefinition.gradientWidth,
                    yCardShimmer - cardAnimationDefinition.gradientWidth
                ),
                end = Offset(xCardShimmer, yCardShimmer)
            )
            Column(
                modifier = Modifier.padding(padding)
            ) {
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(imageHeight)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(imageHeight/10)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(imageHeight/10)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(imageHeight/10)
                            .background(brush = brush)
                    )
                }
            }
        }
    }
}