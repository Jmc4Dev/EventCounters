package com.jmc4dev.eventscounterapp.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Main: Screen("main_screen")
    object BigCounters: Screen("big_counter_screen")
    object SmallCounters: Screen("small_counter_screen")
    object GiveNames: Screen("give_names_screen")
}
