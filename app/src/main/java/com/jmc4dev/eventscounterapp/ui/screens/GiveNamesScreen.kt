package com.jmc4dev.eventscounterapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmc4dev.eventscounterapp.ui.navigation.Screen
import com.jmc4dev.eventscounterapp.viewmodels.GiveNamesViewModel
import eventscounterapp.R

@ExperimentalComposeUiApi
@Composable
fun GiveNamesScreen(
    navController: NavController,
    counters: Int,
    namesViewModel: GiveNamesViewModel
) {
    val countersList = namesViewModel.countersList

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
            text = "Give names to counters",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        for (i in 1..counters) {
            IdentifyCounter(
                counter = i,
                totalCounters = counters,
                name = countersList.value[i - 1].value,
                updateName = { newEntry ->
                    countersList.value[i - 1].value = newEntry
                }
            )
        }
        CountButton(navController = navController, counters = counters)
    }
}

@Composable
fun CountButton(navController: NavController, counters: Int) {
    Button(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = Color.Black
        ),
        border = BorderStroke(width = 2.dp, color = Color.Black),
        onClick = {
            if (counters < 3) {
                navController.popBackStack()
                navController.navigate(Screen.BigCounters.route + "/${counters}")
            }
            else {
                navController.popBackStack()
                navController.navigate(Screen.SmallCounters.route + "/${counters}")
            }
        }) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(R.string.count),
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.Bold
        )
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
