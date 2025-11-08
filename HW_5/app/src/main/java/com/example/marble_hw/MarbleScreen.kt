package com.example.marble_hw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun MarbleScreen(viewModel: MarbleViewModel) {

    val position by viewModel.position.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val density = LocalDensity.current

        LaunchedEffect(maxWidth, maxHeight) {
            with(density) {
                viewModel.maxX = (maxWidth - 40.dp).toPx()   // Ensure marble stays inside
                viewModel.maxY = (maxHeight - 40.dp).toPx()
            }
        }

        // drwa
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        position.first.roundToInt(),
                        position.second.roundToInt()
                    )
                }
                .size(40.dp)
                .background(Color.Blue, CircleShape)
        )
    }
}
