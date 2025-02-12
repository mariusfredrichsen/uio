package com.example.countries.ui.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController

@Composable
fun CountriesScreen(
    countriesScreenViewModel: CountriesScreenViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier
) {
    val countriesUIState by countriesScreenViewModel.countriesUIState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier.weight(1f)
        ) {
            CountriesTopBar(modifier = modifier)
            LazyColumn {
                items(countriesUIState.countries) { c ->
                    CountriesCard(
                        country = c,
                        navController = navController
                    )
                }
            }
        }
        AddCountry(
            countriesScreenViewModel = countriesScreenViewModel
        )


    }
}