package com.jmc4dev.eventscounterapp.viewmodels

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.WindowManager

fun keepScreen(keepOn: Boolean, context: Context) {
    val activity = context as Activity
    if (keepOn)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    else
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}

fun resetScreenData(
    namesViewModel: CountersViewModel,
    mainViewModel: MainViewModel
) {
    namesViewModel.resetCounters()
    mainViewModel.resetTimers()
    mainViewModel.resetCounterList()
    mainViewModel.resetAllLapTimers()
    mainViewModel.resetCounterLaps()
}

fun showDataStored(countersNamesList: CountersViewModel) {
    countersNamesList.countersObjectList.value.forEach { info ->
        var laps = ""
        info.laps.forEach { lap ->
            laps += "$lap - "
        }
        Log.d("COUNTER-INFO", info.counterName)
        Log.d("COUNTER-INFO", info.counterValue.toString())
        Log.d("COUNTER-INFO", laps)
    }
}