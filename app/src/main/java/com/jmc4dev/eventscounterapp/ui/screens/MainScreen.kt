package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.jmc4dev.eventscounterapp.components.CustomButton
import com.jmc4dev.eventscounterapp.ui.CustomSlider
import com.jmc4dev.eventscounterapp.ui.navigation.Screen
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel
import eventscounterapp.R

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val sliderValue = mainViewModel.sliderValue
    val activateTimer = mainViewModel.activateTimer

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier,
                elevation = 4.dp,
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Anunci
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        adSize = AdSize.BANNER
                        adUnitId = context.getString(R.string.ad_id_banner)
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomSlider(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    message = stringResource(R.string.how_many_counters),
                    numberOfOptions = 5,
                    sliderValue = sliderValue.value
                ) { newValue ->
                    sliderValue.value = newValue
                }
                CustomButton(
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    onClick = {
                        navController.navigate(Screen.GiveNames.route + "/${sliderValue.value.toInt()}")
                    },
                    text = stringResource(R.string.identify_counters)
                )

                CustomButton(
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    onClick = {
                        mainViewModel.resetTimers()
                        if (sliderValue.value.toInt() < 3)
                            navController.navigate(Screen.BigCounters.route + "/${sliderValue.value.toInt()}")
                        else
                            navController.navigate(Screen.SmallCounters.route + "/${sliderValue.value.toInt()}")
                    },
                    text = stringResource(R.string.count)
                )

                Row {
                    Text(
                        text = stringResource(R.string.activate_timer),
                        fontSize = 28.sp
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    Switch(
                        checked = activateTimer.value,
                        onCheckedChange = {
                            activateTimer.value = it
                        }
                    )
                }
            }
        }
    }
}
