package no.uio.ifi.in2000.countries.ui.countries

import no.uio.ifi.in2000.countries.model.Country

data class CountriesUIState(
    val countries: List<Country> = listOf(),
)
