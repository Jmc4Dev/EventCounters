package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun SmallCountersScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: GiveNamesViewModel
) {
    var size = 1.dp
    var fontSize = 1.sp
    when (counters) {
        1 -> {
            size = 150.dp
            fontSize = 38.sp
        }
        2 -> {
            size = 150.dp
            fontSize = 38.sp
        }
        3 -> {
            size = 100.dp
            fontSize = 35.sp
        }
        4 -> {
            size = 100.dp
            fontSize = 35.sp
        }
        5 -> {
            size = 80.dp
            fontSize = 32.sp
        }
        6 -> {
            size = 70.dp
            fontSize = 30.sp
        }
    }
    val countersNamesList = namesViewModel.countersList
    val countersList = mutableListOf<MutableState<Int>>()
    for (i in 1..counters){
        countersList.add(rememberSaveable { mutableStateOf(0) })
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..counters) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = countersNamesList.value[i - 1].value,
                    style = MaterialTheme.typography.h4,
                    color = Color.White
                )
                CreateCircle(
                    size = size,
                    fontSize = fontSize,
                    counter = countersList[i - 1].value
                ) { newValue ->
                    countersList[i - 1].value = newValue
                }
            }
        }
    }
}
