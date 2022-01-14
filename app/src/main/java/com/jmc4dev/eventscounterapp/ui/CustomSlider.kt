package com.jmc4dev.eventscounterapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    message: String = "",
    numberOfOptions: Int,
    sliderValue: Float,
    onValueChanged: (Float) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = MaterialTheme.colors.background),
            ) {
                Slider(
                    value = sliderValue,
                    onValueChange = { newValue -> onValueChanged(newValue) },
                    valueRange = 1f..numberOfOptions.toFloat(),
                    steps = numberOfOptions - 2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..numberOfOptions) {
                        Text(
                            text = "$i",
                            style = MaterialTheme.typography.h4,
                        )
                    }
                }
            }
        }
    }
}
