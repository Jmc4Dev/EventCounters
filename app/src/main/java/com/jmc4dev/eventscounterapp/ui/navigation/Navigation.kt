package com.jmc4dev.eventscounterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jmc4dev.eventscounterapp.ui.screens.*
import com.jmc4dev.eventscounterapp.viewmodels.GiveNamesViewModel

@ExperimentalComposeUiApi
@Composable
fun Navigation(namesViewModel: GiveNamesViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.BigCounters.route + "/{counters}",
            arguments = listOf(navArgument("counters") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("counters")
                ?.let { it ->
                    BigCountersScreen(
                        navController = navController,
                        counters = it,
                        namesViewModel = namesViewModel
                    )
                }
        }
        composable(
            route = Screen.SmallCounters.route + "/{counters}",
            arguments = listOf(navArgument("counters") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("counters")
                ?.let { it ->
                    SmallCountersScreen(
                        navController = navController,
                        counters = it,
                        namesViewModel = namesViewModel
                    )
                }
        }
        composable(
            route = Screen.GiveNames.route + "/{counters}",
            arguments = listOf(navArgument("counters") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("counters")
                ?.let { it ->
                    GiveNamesScreen(
                        navController = navController,
                        counters = it,
                        namesViewModel = namesViewModel
                    )
                }

        }
    }
}