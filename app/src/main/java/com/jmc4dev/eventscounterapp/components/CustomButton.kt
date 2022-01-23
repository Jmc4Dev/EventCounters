package com.jmc4dev.eventscounterapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke = BorderStroke(width = 1.dp, color = Color.White),
    onClick: () -> Unit,
    text: String,
    style: TextStyle = MaterialTheme.typography.button,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = colors,
        border = border,
        onClick = {
            onClick()
        }
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = style,
            fontWeight = fontWeight
        )
    }
}

