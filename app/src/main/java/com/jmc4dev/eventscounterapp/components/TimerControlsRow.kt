package com.jmc4dev.eventscounterapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerControlsRow(
    modifier: Modifier = Modifier,
    boxSize: Dp,
    iconSize: Dp,
    runTimer: MutableState<Boolean>,
    scope: CoroutineScope,
    activateTimer: MutableState<Boolean>,
    tenths: MutableState<Int>,
    seconds: MutableState<Int>,
    minutes: MutableState<Int>,
    delayTime: Long = 1000L,
    tenthsLap: MutableState<Int>,
    secondsLap: MutableState<Int>,
    minutesLap: MutableState<Int>
) {
    if (activateTimer.value) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (runTimer.value) {
                Box(modifier = Modifier
                    .size(boxSize)
                    .clickable {
                        runTimer.value = false
                    }
                    .background(Color(0xFFB90202))
                )
            } else {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .clickable {
                            runTimer.value = true
                            scope.launch {
                                while (activateTimer.value && runTimer.value) {
                                    delay(delayTime)
                                    if (tenths.value < 9)
                                        tenths.value += 1
                                    else {
                                        tenths.value = 0
                                        if (seconds.value < 59)
                                            seconds.value += 1
                                        else {
                                            seconds.value = 0
                                            minutes.value += 1
                                        }
                                    }
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
                            if (activateTimer.value && !runTimer.value) {
                                tenths.value = 0
                                seconds.value = 0
                                minutes.value = 0
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