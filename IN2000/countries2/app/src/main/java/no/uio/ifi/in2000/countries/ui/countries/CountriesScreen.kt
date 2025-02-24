package no.uio.ifi.in2000.countries.ui.countries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CountriesScreen(countriesScreenViewModel: CountriesScreenViewModel = viewModel(), modifier: Modifier) {
    val countriesUIState by countriesScreenViewModel.countriesUIState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            // TopAppBar
            CountriesTopBar(modifier = modifier)

            // List with countries card
            LazyColumn {
                items(countriesUIState.countries) { c ->
                    CountryCard(
                        country = c
                    )
                }
            }
        }
        // Add country component
        var text by remember { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                }
            )
            Button(
                onClick = {
                    countriesScreenViewModel.addCountry(text)
                }
            ) {
                Text(
                    text = "Add Country"
                )
            }
        }
    }
}