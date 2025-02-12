package com.example.countries.ui.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountriesScreen(countriesScreenViewModel: CountriesScreenViewModel = viewModel(), modifier: Modifier) {
    val countriesUIState by countriesScreenViewModel.countriesUIState.collectAsState()
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxHeight()
    ) {

        Column {
            CountriesTopBar(modifier = modifier)
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top
            ) {
                items(countriesUIState.countries) { c ->
                    CountriesCard(c)
                }
            }
        }



    }
    AddCountry(
        countriesScreenViewModel = countriesScreenViewModel
    )
}