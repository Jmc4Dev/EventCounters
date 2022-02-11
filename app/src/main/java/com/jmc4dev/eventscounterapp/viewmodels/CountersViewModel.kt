package com.jmc4dev.eventscounterapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmc4dev.eventscounterapp.models.CounterInfo
import com.jmc4dev.eventscounterapp.models.ResultsInfo
import kotlinx.coroutines.launch
import java.util.stream.IntStream.range

class CountersViewModel(): ViewModel() {
    val countersObjectList: MutableState<MutableList<CounterInfo>> = mutableStateOf(mutableListOf())
    val resultsList: MutableList<ResultsInfo> = mutableListOf()

    init{
        viewModelScope.launch {
            for (i in 1..5) {
                countersObjectList.value.add(CounterInfo())
            }
        }
    }

    fun resetCounters() {
        countersObjectList.value.forEach { counterInfo ->
            counterInfo.counterValue = 0
            counterInfo.laps = mutableListOf()
            counterInfo.totalTime = 0
        }
    }

    fun prepareDataForResults() {
        var lap = 0
        countersObjectList.value.forEach { counterInfo ->
            for (i in range(0,counterInfo.laps.size)) {

            }
        }
    }
}
