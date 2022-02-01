package com.jmc4dev.eventscounterapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmc4dev.eventscounterapp.ui.navigation.Navigation
import com.jmc4dev.eventscounterapp.ui.theme.EventsCounterAppTheme
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val namesViewModel: CountersViewModel = viewModel()
            val mainViewModel: MainViewModel = viewModel()

            EventsCounterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Navigation(
                        namesViewModel = namesViewModel,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}
