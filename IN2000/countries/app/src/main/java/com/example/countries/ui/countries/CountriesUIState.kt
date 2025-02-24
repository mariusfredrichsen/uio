package com.example.countries.ui.countries

import com.example.countries.model.Country

data class CountriesUIState(
    val countries: List<Country> = listOf(),
    val selectedCountry: Country? = null,
    val countryText: String = "",
)
