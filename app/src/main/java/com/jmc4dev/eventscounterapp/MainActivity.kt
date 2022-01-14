package com.jmc4dev.eventscounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmc4dev.eventscounterapp.ui.navigation.Navigation
import com.jmc4dev.eventscounterapp.ui.theme.EventsCounterAppTheme
import com.jmc4dev.eventscounterapp.ui.theme.Purple500
import com.jmc4dev.eventscounterapp.viewmodels.GiveNamesViewModel

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val namesViewModel: GiveNamesViewModel = viewModel()

            EventsCounterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (isSystemInDarkTheme()) Color.Black else Purple500     //Color(0xFF546E7A)
                ) {
                    Navigation(namesViewModel = namesViewModel)
                }
            }
        }
    }
}
