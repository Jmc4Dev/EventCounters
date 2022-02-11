package com.jmc4dev.eventscounterapp.models

data class ResultsInfo(
    var lapNumber: String = "",
    var laps: MutableList<String> = mutableListOf()
)
