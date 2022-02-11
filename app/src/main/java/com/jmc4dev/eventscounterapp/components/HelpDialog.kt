package com.jmc4dev.eventscounterapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import eventscounterapp.R

@Composable
fun HelpDialog(isHelpVisible: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = {}
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color(0xFFF2F2F2),
            contentColor = Color.DarkGray,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.help),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(id = R.string.help_message_1))
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { isHelpVisible.value = !isHelpVisible.value }
                ) {
                    Text(text = stringResource(id = R.string.got_it))
                }
            }
        }
    }
}
