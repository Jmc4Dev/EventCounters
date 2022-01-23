package com.jmc4dev.eventscounterapp.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var sliderValue: MutableState<Float> = mutableStateOf(1f)

    var tenthsOfSecond: MutableState<Int> = mutableStateOf(0)
    var seconds: MutableState<Int> = mutableStateOf(0)
    var minutes: MutableState<Int> = mutableStateOf(0)
    var tenthsOfSecondLap: MutableState<Int> = mutableStateOf(0)
    var secondsLap: MutableState<Int> = mutableStateOf(0)
    var minutesLap: MutableState<Int> = mutableStateOf(0)

    var activateTimer: MutableState<Boolean> = mutableStateOf(false)
    var runTimer: MutableState<Boolean> = mutableStateOf(false)

    fun resetTimers() {
        tenthsOfSecond = mutableStateOf(0)
        seconds = mutableStateOf(0)
        minutes = mutableStateOf(0)
        tenthsOfSecondLap = mutableStateOf(0)
        secondsLap = mutableStateOf(0)
        minutesLap = mutableStateOf(0)
    }
}