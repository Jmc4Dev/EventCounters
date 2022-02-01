package com.jmc4dev.eventscounterapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var sliderValue: MutableState<Float> = mutableStateOf(1f)

    val countersList = mutableListOf<MutableState<Int>>()
    var countersLaps = mutableListOf<MutableState<Int>>()
    var mainTimer: MutableState<Int> = mutableStateOf(0)
    var activateTimer: MutableState<Boolean> = mutableStateOf(false)
    var runTimer: MutableState<Boolean> = mutableStateOf(false)

    init {
        viewModelScope.launch {
            for (i in 1..5) {
                countersLaps.add(mutableStateOf(0))
                countersList.add(mutableStateOf(0))
            }
        }
    }

    fun getTimeFormatted(value: Int): String {
        val minutes: Int = (value / 10) / 60
        val seconds: Int = (value / 10) % 60
        val tenthsOfSecond: Int = value % 10
        val minutesFormatted = String.format("%02d", minutes)
        val secondsFormatted = String.format("%02d", seconds)
        val tenthsFormatted = String.format("%01d", tenthsOfSecond)
        return "$minutesFormatted:$secondsFormatted.$tenthsFormatted"
    }

    fun updateCounterLaps() {
        for (i in 1..sliderValue.value.toInt()) {
            countersLaps[i - 1].value += 1
        }
    }

    fun resetCounterList() {
        for (i in 1..sliderValue.value.toInt()) {
            countersList[i - 1].value = 0
        }
    }

    fun resetCounterLaps() {
        for (i in 1..sliderValue.value.toInt()) {
            countersLaps[i - 1].value = 0
        }
    }

    fun resetTimers() {
        mainTimer.value = 0
        if (countersLaps.size > 0)
            resetAllLapTimers()
    }

    fun resetAllLapTimers() {
        for (i in 1..sliderValue.value.toInt()) {
            countersLaps[i - 1].value = 0
        }
    }

    fun resetLapTimer(position: Int) {
        countersLaps[position].value = 0
    }
}