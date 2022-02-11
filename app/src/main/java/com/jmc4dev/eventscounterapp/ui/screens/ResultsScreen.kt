package com.jmc4dev.eventscounterapp.ui.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.jmc4dev.eventscounterapp.utils.getTimeFormatted
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import eventscounterapp.R

@Composable
fun ResultsScreen(navController: NavController, namesViewModel: CountersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier,
                elevation = 4.dp,
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
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
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.results),
                style = MaterialTheme.typography.h3
            )
            LazyColumn() {
                items(1) {
                    LazyRow(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        items(namesViewModel.countersObjectList.value) { item ->
                            if (item.totalTime > 0) {
                                Column(
                                    modifier = Modifier
                                        .border(width = 2.dp, color = Color.DarkGray)
                                        .padding(4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            text = item.counterName,
                                            style = MaterialTheme.typography.h6,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            var pos = 0
                                            item.laps.forEach { lap ->
                                                Text(
                                                    modifier = Modifier
                                                        .padding(
                                                            vertical = 4.dp,
                                                            horizontal = 6.dp
                                                        ),
                                                    text = lap,
                                                    fontSize = 18.sp
                                                )
                                                if (item.laps.size - 1 == pos) {
                                                    Text(
                                                        modifier = Modifier
                                                            .background(color = Color(0xFFBDDAB3))
                                                            .padding(horizontal = 2.dp),
                                                        text = getTimeFormatted(item.totalTime),
                                                        style = MaterialTheme.typography.h6,
                                                    )
                                                }
                                                pos += 1
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}