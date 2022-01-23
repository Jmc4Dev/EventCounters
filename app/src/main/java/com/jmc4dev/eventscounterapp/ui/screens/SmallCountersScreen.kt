package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.components.TimerControlsRow
import com.jmc4dev.eventscounterapp.ui.CreateCircle
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel
import eventscounterapp.R
import kotlinx.coroutines.Dispatchers

@Composable
fun SmallCountersScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: CountersViewModel,
    mainViewModel: MainViewModel
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
            size = 90.dp
            fontSize = 33.sp
        }
    }

    val countersNamesList = namesViewModel.countersList
    val countersList = mutableListOf<MutableState<Int>>()
    for (i in 1..counters) {
        countersList.add(rememberSaveable { mutableStateOf(0) })
    }
    val activateTimer = mainViewModel.activateTimer
    val runTimer = mainViewModel.runTimer
    val tenths = mainViewModel.tenthsOfSecond
    val seconds = mainViewModel.seconds
    val minutes = mainViewModel.minutes
    val tenthsLap = mainViewModel.tenthsOfSecondLap
    val secondsLap = mainViewModel.secondsLap
    val minutesLap = mainViewModel.minutesLap

    val scope = rememberCoroutineScope { Dispatchers.Main }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier,
                elevation = 4.dp,
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { navController.popBackStack() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (activateTimer.value) {
                TimerControlsRow(
                    boxSize = 40.dp,
                    iconSize = 60.dp,
                    runTimer = runTimer,
                    scope = scope,
                    activateTimer = activateTimer,
                    tenths = tenths,
                    seconds = seconds,
                    minutes = minutes,
                    delayTime = 99L,
                    tenthsLap = tenthsLap,
                    secondsLap = secondsLap,
                    minutesLap = minutesLap
                )
            }
            for (i in 1..counters) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = countersNamesList.value[i - 1].value,
                            style = MaterialTheme.typography.h4,
                            color = Color.DarkGray
                        )
                        if (activateTimer.value) {
                            val minutesFormatted = String.format("%02d", minutes.value)
                            val secondsFormatted = String.format("%02d", seconds.value)
                            val tenthsFormatted = String.format("%01d", tenths.value)
                            Text(
                                text = if (runTimer.value) "$minutesFormatted:$secondsFormatted"
                                else "$minutesFormatted:$secondsFormatted.$tenthsFormatted",
                                fontSize = 18.sp
                            )
                        }
                    }
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
}

