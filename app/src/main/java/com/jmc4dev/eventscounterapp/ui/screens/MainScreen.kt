package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.ui.CustomSlider
import com.jmc4dev.eventscounterapp.ui.navigation.Screen
import eventscounterapp.R

@Composable
fun MainScreen(navController: NavController) {
    var sliderValue by remember { mutableStateOf(1f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomSlider(
            modifier = Modifier.padding(horizontal = 40.dp),
            message = stringResource(R.string.how_many_counters),
            numberOfOptions = 6,
            sliderValue = sliderValue
        ) { newValue ->
            sliderValue = newValue
        }
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black
            ),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            onClick = {
                navController.navigate(Screen.GiveNames.route + "/${sliderValue.toInt()}")
            }) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.identify_counters),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black
            ),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            onClick = {
                if (sliderValue.toInt() < 3)
                    navController.navigate(Screen.BigCounters.route + "/${sliderValue.toInt()}")
                else
                    navController.navigate(Screen.SmallCounters.route + "/${sliderValue.toInt()}")
            }) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.count),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
