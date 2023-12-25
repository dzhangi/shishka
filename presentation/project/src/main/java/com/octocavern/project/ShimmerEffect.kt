package com.octocavern.project

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.octocavern.ui.theme.ShishkaTheme

@Composable
fun ShimmerWrapper(
    isLoading: Boolean,
    shimmerWidth: Dp = 128.dp,
    shimmerHeight: Dp = 16.dp,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .width(shimmerWidth)
                .height(shimmerHeight)
                .padding(vertical = 2.dp)
                .shimmerAnimation()
        )
    } else {
        content()
    }
}

fun Modifier.shimmerAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {
        val shimmerColors = ShimmerAnimationData(isLightMode = true).getColors()
        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )

        background(
            shape = RoundedCornerShape(8.dp),
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
        )
    }
}

data class ShimmerAnimationData(
    private val isLightMode: Boolean
) {
    fun getColors(): List<Color> {
        return if (isLightMode) {
            val color = Color.White

            listOf(
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 1.0f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
            )
        } else {
            val color = Color.Black

            listOf(
                color.copy(alpha = 0.0f),
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.0f),
            )
        }
    }
}

@Composable
@Preview
fun PreviewWrapper() {
    ShishkaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                ShimmerWrapper(isLoading = true) {
                    Text(text = "Some stupid shit...")
                }
                ShimmerWrapper(isLoading = false) {
                    Text(text = "Another stupid shit...")
                }
            }
        }
    }
}