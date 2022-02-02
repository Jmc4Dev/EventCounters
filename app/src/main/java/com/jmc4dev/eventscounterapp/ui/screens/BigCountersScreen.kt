package com.jmc4dev.eventscounterapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.components.TimerControlsRow
import com.jmc4dev.eventscounterapp.ui.CreateCircle
import com.jmc4dev.eventscounterapp.utils.getTimeFormatted
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel
import com.jmc4dev.eventscounterapp.viewmodels.keepScreen
import com.jmc4dev.eventscounterapp.viewmodels.resetScreenData
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
    val countersNamesList = namesViewModel.countersObjectList
    val countersList =  mainViewModel.countersList

    val activateTimer = mainViewModel.activateTimer
    val mainTimer = mainViewModel.mainTimer
    val runTimer = mainViewModel.runTimer
    val scope = rememberCoroutineScope { Dispatchers.Main }
    // turn on the keep_screen_on flag for this screen
    val context = LocalContext.current
    keepScreen(true, context)

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
                        modifier = Modifier.clickable {
                            // turn on the keep_screen_on flag for this screen
                            keepScreen(false, context)
                            resetScreenData(
                                namesViewModel = namesViewModel,
                                mainViewModel = mainViewModel
                            )
                            navController.popBackStack()
                        },
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
                    navController = navController,
                    boxSize = 40.dp,
                    iconSize = 64.dp,
                    mainViewModel = mainViewModel,
                    countersNamesList = namesViewModel,
                    scope = scope,
                    delayTime = 97L
                )
            }
            if (activateTimer.value) {
                val timeFormatted = getTimeFormatted(mainTimer.value)
                Text(
                    text = timeFormatted,
                    fontSize = 24.sp
                )
            }
            for (i in 1..counters) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = countersNamesList.value[i - 1].counterName,
                        style = MaterialTheme.typography.h3,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CreateCircle(
                        size = 130.dp,
                        fontSize = 36.sp,
                        counter = countersList[i - 1].value
                    ) { newValue ->
                        // When the user needs the timers, do not allow to click the counter if
                        // the  timers are not running
                        if (runTimer.value || !activateTimer.value) {
                            countersList[i - 1].value = newValue
                            countersNamesList.value[i - 1].counterValue = newValue
                            countersNamesList.value[i - 1].laps.add(
                                getTimeFormatted(mainViewModel.countersLaps[i - 1].value)
                            )
                            countersNamesList.value[i - 1].totalTime += mainViewModel.countersLaps[i - 1].value
                            mainViewModel.resetLapTimer(i - 1)
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    if (activateTimer.value) {
                        Row(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (countersNamesList.value[i - 1].laps.size > 0) countersNamesList.value[i - 1].laps.last()
                                else "00:00",
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = if (mainViewModel.countersLaps.size > 0) getTimeFormatted(
                                    mainViewModel.countersLaps[i - 1].value
                                )
                                else "00:00",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

