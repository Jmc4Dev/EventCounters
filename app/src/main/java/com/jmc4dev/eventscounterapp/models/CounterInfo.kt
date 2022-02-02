package com.jmc4dev.eventscounterapp.models

data class CounterInfo(
    var counterName: String = "",
    var counterValue: Int = 0,
    var laps: MutableList<String> = mutableListOf(),
    var totalTime: Int = 0
)

