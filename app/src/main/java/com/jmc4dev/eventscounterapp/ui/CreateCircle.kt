package com.jmc4dev.eventscounterapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CreateCircle(size: Dp, fontSize: TextUnit, counter: Int = 1, updateCounter: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .clickable {
                updateCounter(counter + 1)
            }
            .size(size),
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$counter",
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

