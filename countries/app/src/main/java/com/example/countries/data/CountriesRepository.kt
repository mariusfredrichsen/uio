package com.example.countries.data

import com.example.countries.model.Country
import com.example.countries.model.CountryInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CountriesRepository {
    private val _countriesDataSource = CountriesDataSource()

    suspend fun getCountry(countryName: String): Country? {
        return _countriesDataSource.fetchCountriesData(countryName)
    }
}