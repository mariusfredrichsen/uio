package com.example.countries.ui.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countries.model.Country

@Composable
fun CountriesCard(country: Country, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        onClick = {
            navController.navigate("country/${country.name}")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = country.name,
            )
            Text(
                text = country.population.toString(),
            )
        }
    }
}