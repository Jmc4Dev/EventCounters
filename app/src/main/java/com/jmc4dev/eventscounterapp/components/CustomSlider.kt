package com.jmc4dev.eventscounterapp.ui

import android.app.Dialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jmc4dev.eventscounterapp.components.HelpDialog
import eventscounterapp.R

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    message: String = "",
    numberOfOptions: Int,
    sliderValue: Float,
    onValueChanged: (Float) -> Unit
) {
    val isHelpVisible = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                color = Color.DarkGray
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        isHelpVisible.value = !isHelpVisible.value
                    },
                imageVector = Icons.Default.Info,
                contentDescription = "Help Icon",
                tint = Color.DarkGray
            )
        }
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
                    .background(color = Color.White),
            ) {
                Slider(
                    value = sliderValue,
                    onValueChange = { newValue -> onValueChanged(newValue) },
                    valueRange = 1f..numberOfOptions.toFloat(),
                    steps = if (numberOfOptions >= 2) numberOfOptions - 2 else 0
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
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
        if (isHelpVisible.value) {
            HelpDialog(isHelpVisible)
        }
    }
}
