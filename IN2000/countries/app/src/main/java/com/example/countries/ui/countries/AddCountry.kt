package com.example.countries.ui.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddCountry(countriesScreenViewModel: CountriesScreenViewModel) {
    val countriesUIState by countriesScreenViewModel.countriesUIState.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
    ) {
        OutlinedTextField(
            value = countriesUIState.countryText,
            onValueChange = {
                countriesScreenViewModel.updateText(
                    text = it
                )
            },
            modifier = Modifier
                .weight(0.50f)
        )
        Button(
            modifier = Modifier
                .weight(0.50f),
            onClick = {
                countriesScreenViewModel.addCountry(countriesUIState.countryText)

            }
        ) {
            Row {
                Text(
                    text = "Add Country"
                )
            }
        }
    }
}