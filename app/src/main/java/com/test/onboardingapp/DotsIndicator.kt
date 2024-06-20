package com.test.onboardingapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int, whiteTransparentColor: Color, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.background(color = Color.Transparent)
    ) {
        for (i in 0 until totalDots) {
            Canvas(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(8.dp)
                    .width(if (i != selectedIndex) 8.dp else 12.dp),
                onDraw = {
                    if (i != selectedIndex) {
                        drawCircle(
                            color = whiteTransparentColor,
                            radius = size.width / 2f
                        )
                    } else if (i == selectedIndex) {
                        drawLine(
                            color = Color.White,
                            start = Offset(0f, size.height / 2),
                            end = Offset(size.width, size.height / 2),
                            cap = StrokeCap.Round,
                            strokeWidth = 8.dp.toPx() // Adjust the thickness of the line as needed
                        )
                    }
                }
            )
        }
    }
}
