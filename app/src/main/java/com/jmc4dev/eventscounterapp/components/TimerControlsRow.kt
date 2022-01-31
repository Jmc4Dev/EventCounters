package com.jmc4dev.eventscounterapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.ui.navigation.Screen
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel
import com.jmc4dev.eventscounterapp.viewmodels.resetScreenData
import com.jmc4dev.eventscounterapp.viewmodels.showDataStored
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerControlsRow(
    navController: NavController,
    modifier: Modifier = Modifier,
    boxSize: Dp,
    iconSize: Dp,
    mainViewModel: MainViewModel,
    countersNamesList: CountersViewModel,
    scope: CoroutineScope,
    delayTime: Long = 1000L
) {
    if (mainViewModel.activateTimer.value) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (mainViewModel.runTimer.value) {
                Box(modifier = Modifier
                    .size(boxSize)
                    .clickable {
                        mainViewModel.runTimer.value = false
                        showDataStored(countersNamesList)
                        navController.navigate(Screen.Results.route)
                    }
                    .background(Color(0xFFB90202))
                )
            } else {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .clickable {
                            mainViewModel.runTimer.value = true
                            scope.launch {
                                while (mainViewModel.activateTimer.value && mainViewModel.runTimer.value) {
                                    delay(delayTime)
                                    mainViewModel.mainTimer.value += 1
                                    mainViewModel.updateCounterLaps()
                                }
                            }
                        },
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Icon",
                    tint = Color(0xFF009688)
                )
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .clickable {
                            if (mainViewModel.activateTimer.value) {
                                resetScreenData(
                                    namesViewModel = countersNamesList,
                                    mainViewModel = mainViewModel
                                )
                            }
                        },
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Restart Icon",
                    tint = Color(0xFF009688)
                )
            }
        }
    }
}

