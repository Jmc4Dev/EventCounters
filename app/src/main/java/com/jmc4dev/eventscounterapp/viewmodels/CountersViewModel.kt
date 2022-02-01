package com.jmc4dev.eventscounterapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmc4dev.eventscounterapp.models.CounterInfo
import kotlinx.coroutines.launch

class CountersViewModel(): ViewModel() {
    val countersObjectList: MutableState<MutableList<CounterInfo>> = mutableStateOf(mutableListOf())

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
        }
    }
}
