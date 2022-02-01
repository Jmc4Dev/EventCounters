package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.components.CustomButton
import com.jmc4dev.eventscounterapp.ui.navigation.Screen
import com.jmc4dev.eventscounterapp.viewmodels.CountersViewModel
import com.jmc4dev.eventscounterapp.viewmodels.MainViewModel
import eventscounterapp.R

@ExperimentalComposeUiApi
@Composable
fun GiveNamesScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: CountersViewModel,
    mainViewModel: MainViewModel
) {
    val countersList = namesViewModel.countersObjectList

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
                        modifier = Modifier.clickable { navController.popBackStack() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                text = stringResource(id = R.string.give_names_to_counters),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(counters) { i ->
                    val name = remember { mutableStateOf(countersList.value[i].counterName) }
                    IdentifyCounter(
                        counter = i + 1,
                        totalCounters = counters,
                        name = name.value,
                        updateName = { newEntry ->
                            if (newEntry.length < 11) {
                                name.value = newEntry
                                countersList.value[i].counterName = newEntry
                            }
                            else {
                                name.value = name.value
                                countersList.value[i].counterName = name.value
                            }
                        }
                    )
                }
            }

            CustomButton(
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                border = BorderStroke(width = 2.dp, color = Color.Black),
                onClick = {
                    mainViewModel.resetTimers()
                    if (counters < 3) {
                        navController.popBackStack()
                        navController.navigate(Screen.BigCounters.route + "/${counters}")
                    } else {
                        navController.popBackStack()
                        navController.navigate(Screen.SmallCounters.route + "/${counters}")
                    }
                },
                text = stringResource(R.string.count)
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun IdentifyCounter(counter: Int, totalCounters: Int, name: String, updateName: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 12.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
        placeholder = {
            Text(text = "Counter $counter", color = Color.DarkGray)
        },
        value = name,
        onValueChange = { newName -> updateName(newName) },
        maxLines = 1,
        textStyle = MaterialTheme.typography.h5,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
        keyboardActions = KeyboardActions(
            onNext = {
                if (counter == totalCounters) {
                    keyboardController?.hide()
                    focusManager.moveFocus(FocusDirection.Down)
                } else
                    focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words
        )
    )
}
