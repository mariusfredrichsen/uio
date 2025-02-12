package no.uio.ifi.in2000.mafredri.oblig1.ui.palindrome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import no.uio.ifi.in2000.mafredri.oblig1.PalindromeViewModel
import no.uio.ifi.in2000.mafredri.oblig1.isPalindrome

@Composable
fun PalindromeScreen(onNavigateToUnitConverter: () -> Unit, textViewModel: PalindromeViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PalindromeChecker(textViewModel)

    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { onNavigateToUnitConverter() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffd18700),
                contentColor = Color.Black
            )
        ) {
            Text("Gå til UnitConverter skjermen")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(textViewModel: PalindromeViewModel) {
    val value by textViewModel.textUIState.collectAsState()
    var topText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText
        )
        OutlinedTextField(
            value = value.text,
            onValueChange = { textViewModel.update(it) },
            label = {Text("Skriv inn tekst her")},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                topText = if (isPalindrome(value.text)) {
                    "Er palindrom"
                } else {
                    "Er ikke palindrom"
                }
            }),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedLabelColor =  Color.Black,
                unfocusedIndicatorColor = Color(0xffd18700),
                focusedIndicatorColor = Color(0xffd18700),
                focusedLabelColor = Color.Black,
                cursorColor = Color(0xffd18700),
                focusedTrailingIconColor = Color(0xffd18700),
            ),
            trailingIcon = null,

        )
        Button(onClick = {
            keyboardController?.hide()
            topText = if (isPalindrome(value.text)) {
                "Er palindrom"
            } else {
                "Er ikke palindrom"
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffd18700),
                contentColor = Color.Black
            )
        ) {
            Text("Sjekk om tekst er palindrom")
        }
    }
}