package com.jmc4dev.eventscounterapp.models

data class CounterInfo(
    val counterName: String = "",
    val counterValue: Int = 0,
    val laps: MutableList<String> = mutableListOf()
)
