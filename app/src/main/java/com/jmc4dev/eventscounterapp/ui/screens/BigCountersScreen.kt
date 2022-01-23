package com.jmc4dev.eventscounterapp.ui.screens

import android.annotation.SuppressLint
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

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BigCountersScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: CountersViewModel,
    mainViewModel: MainViewModel
) {
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
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (activateTimer.value) {
                TimerControlsRow(
                    boxSize = 40.dp,
                    iconSize = 64.dp,
                    runTimer = runTimer,
                    scope = scope,
                    activateTimer = activateTimer,
                    tenths = tenths,
                    seconds = seconds,
                    minutes = minutes,
                    delayTime = 98L,
                    tenthsLap = tenthsLap,
                    secondsLap = secondsLap,
                    minutesLap = minutesLap
                )
            }
            for (i in 1..counters) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = countersNamesList.value[i - 1].value,
                        style = MaterialTheme.typography.h3,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CreateCircle(
                        size = 140.dp,
                        fontSize = 38.sp,
                        counter = countersList[i - 1].value
                    ) { newValue ->
                        countersList[i - 1].value = newValue
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    if (activateTimer.value) {
                        val minutesFormatted = String.format("%02d", minutes.value)
                        val secondsFormatted = String.format("%02d", seconds.value)
                        val tenthsFormatted = String.format("%01d", tenths.value)
                        Text(
                            text = if (runTimer.value) "$minutesFormatted:$secondsFormatted"
                            else "$minutesFormatted:$secondsFormatted.$tenthsFormatted",
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}
