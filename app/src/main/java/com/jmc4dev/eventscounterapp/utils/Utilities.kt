package com.jmc4dev.eventscounterapp.utils

fun getTimeFormatted(value: Int): String {
    val minutes: Int = (value / 10) / 60
    val seconds: Int = (value / 10) % 60
    val tenthsOfSecond: Int = value % 10
    val minutesFormatted = String.format("%02d", minutes)
    val secondsFormatted = String.format("%02d", seconds)
    val tenthsFormatted = String.format("%01d", tenthsOfSecond)
    return "$minutesFormatted:$secondsFormatted.$tenthsFormatted"
}

