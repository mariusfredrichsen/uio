package no.uio.ifi.in2000.countries.data

import no.uio.ifi.in2000.countries.model.Country

class CountriesRepository {
    private val _countriesDataSource = CountriesDataSource()

    suspend fun getCountry(countryName: String): Country? {
        return _countriesDataSource.fetchCountriesData(
            countryName = countryName
        )
    }

}