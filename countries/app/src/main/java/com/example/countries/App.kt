package com.example.countries

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.countries.ui.countries.CountriesScreen
import com.example.countries.ui.countries.CountriesTopBar

@Composable
fun App(modifier: Modifier) {
    CountriesScreen(modifier = modifier)
}