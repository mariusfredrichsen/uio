package no.uio.ifi.in2000.mafredri.oblig1.ui.unitconverter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.uio.ifi.in2000.mafredri.oblig1.UnitConverterViewModel
import kotlin.math.roundToInt

@Composable
fun UnitConverterScreen(onNavigateToPalindrome: () -> Unit, textViewModel: UnitConverterViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UnitConverter(textViewModel)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { onNavigateToPalindrome() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffd18700),
                contentColor = Color.Black
            )
        ) {
            Text("Gå til Palindrom skjermen")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UnitConverter(textViewModel: UnitConverterViewModel) {
    val value by textViewModel.textUIState.collectAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val units = listOf("Ounce", "Cup", "Gallon", "Hogheads")
    var pickedUnit by remember { mutableStateOf("") }
    var liters by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(if (liters != "") {
            "Antall liter: $liters"
        } else {
            ""
        })
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = it}
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .padding(8.dp),
                readOnly = true,
                value = pickedUnit,
                onValueChange = {},
                label = { Text(text ="Velg en enhet som skal konverteres til liter", fontSize = 14.sp) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedLabelColor =  Color.Black,
                    unfocusedIndicatorColor = Color(0xffd18700),
                    focusedIndicatorColor = Color(0xffd18700),
                    focusedLabelColor = Color.Black,
                    cursorColor = Color(0xffd18700),
                    focusedTrailingIconColor = Color(0xffd18700),
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                units.filter{it != pickedUnit}.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            pickedUnit = unit
                            expanded = false
                            if (value.text != "") {
                                liters = when (pickedUnit) {
                                    "Ounce" -> converter(
                                        value.text.toInt(),
                                        ConverterUnits.values()[units.indexOf("Ounce")]
                                    ).toString()

                                    "Cup" -> converter(
                                        value.text.toInt(),
                                        ConverterUnits.values()[units.indexOf("Cup")]
                                    ).toString()

                                    "Gallon" -> converter(
                                        value.text.toInt(),
                                        ConverterUnits.values()[units.indexOf("Gallon")]
                                    ).toString()

                                    "Hogheads" -> converter(
                                        value.text.toInt(),
                                        ConverterUnits.values()[units.indexOf("Hogheads")]
                                    ).toString()

                                    else -> "0"
                                }
                            }
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
        OutlinedTextField(
            value = value.text,
            onValueChange = { textViewModel.update(it)},
            label = { Text("Skriv inn antall av enheten") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(onDone = {
                liters = when (pickedUnit) {
                    "Ounce" -> converter(value.text.toDouble().roundToInt(), ConverterUnits.values()[units.indexOf("Ounce")]).toString()
                    "Cup" -> converter(value.text.toDouble().roundToInt(), ConverterUnits.values()[units.indexOf("Cup")]).toString()
                    "Gallon" -> converter(
                        value.text.toDouble().roundToInt(),
                        ConverterUnits.values()[units.indexOf("Gallon")]
                    ).toString()

                    "Hogheads" -> converter(
                        value.text.toDouble().roundToInt(),
                        ConverterUnits.values()[units.indexOf("Hogheads")]
                    ).toString()

                    else -> "0"
                }
                keyboardController?.hide()
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
    }
}