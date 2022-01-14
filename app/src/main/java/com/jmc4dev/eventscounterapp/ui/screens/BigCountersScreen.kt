package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.ui.CreateCircle
import com.jmc4dev.eventscounterapp.viewmodels.GiveNamesViewModel

@Composable
fun BigCountersScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: GiveNamesViewModel
) {
    val countersNamesList = namesViewModel.countersList
    val countersList = mutableListOf<MutableState<Int>>()
    for (i in 1..counters){
        countersList.add(rememberSaveable { mutableStateOf(0) })
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..counters) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = countersNamesList.value[i - 1].value,
                    style = MaterialTheme.typography.h3,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                CreateCircle(
                    size = 150.dp,
                    fontSize = 38.sp,
                    counter = countersList[i - 1].value
                ) { newValue ->
                    countersList[i - 1].value = newValue
                }
            }
        }
    }
}
