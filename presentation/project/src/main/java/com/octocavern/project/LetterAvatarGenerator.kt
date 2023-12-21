package com.octocavern.project

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octocavern.ui.theme.ShishkaTheme
import kotlin.random.Random

@Composable
fun LetterAvatar(letter: Char, modifier: Modifier = Modifier) {
    val randomColor = generateRandomColor()

    Box(
        modifier = modifier.size(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = randomColor,
                radius = (canvasWidth / 2).coerceAtMost(canvasHeight / 2),
                center = Offset(canvasWidth / 2, canvasHeight / 2)
            )

            drawTextCentered(
                text = letter.uppercase(),
                x = canvasWidth / 2,
                y = canvasHeight / 2,
                fontSize = canvasWidth * 0.5f,
                color = Color.White
            )
        }
    }
}

private fun generateRandomColor(): Color {
    val random = Random.Default
    return Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
}

private fun DrawScope.drawTextCentered(
    text: String,
    x: Float,
    y: Float,
    fontSize: Float,
    color: Color
) {
    drawIntoCanvas { canvas ->
        val paint = Paint().asFrameworkPaint().apply {
            this.textSize = fontSize
            this.color = color.toArgb()
            this.isAntiAlias = true
            this.textAlign = android.graphics.Paint.Align.CENTER
            this.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        val offset = android.graphics.Paint.FontMetrics().run {
            paint.getFontMetrics(this)
            (ascent + descent) / 2
        }

        canvas.nativeCanvas.drawText(text, x, y - offset, paint)
    }
}

@Composable
@Preview
fun PreviewAvatar() {
    ShishkaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LetterAvatar(letter = 'K', modifier = Modifier.size(12.dp))
        }
    }
}