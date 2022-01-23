package com.jmc4dev.eventscounterapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountersViewModel(): ViewModel() {
    val countersList: MutableState<MutableList<MutableState<String>>> = mutableStateOf(mutableListOf())

    init{
        viewModelScope.launch {
            for (i in 1..5) {
                countersList.value.add(mutableStateOf(""))
            }
        }
    }
}