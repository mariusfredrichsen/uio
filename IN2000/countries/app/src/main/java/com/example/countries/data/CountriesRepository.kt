package com.example.countries.data

import com.example.countries.model.Country


class CountriesRepository {
    private val _countriesDataSource = CountriesDataSource()

    suspend fun getCountry(countryName: String): Country? {
        return _countriesDataSource.fetchCountriesData(countryName)
    }
}