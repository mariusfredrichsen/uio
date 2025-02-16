package no.uio.ifi.in2000.countries.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import no.uio.ifi.in2000.countries.model.Country
import no.uio.ifi.in2000.countries.model.CountryAPI
import java.net.UnknownHostException

class CountriesDataSource {
    private val url: String = "https://d6wn6bmjj722w.population.io/1.0/population/%s/today-and-tomorrow/"

    suspend fun fetchCountriesData(countryName: String): Country? {
        val countryInfo: CountryAPI? = try {
            val response: HttpResponse = APIClient.client.get(
                String.format(url, countryName)
            )

            if (response.status.value != 200) {
                null
            } else {
                response.body()
            }
        } catch (e: UnknownHostException) {
            null
        }

        return if (countryInfo != null) {
            Country(
                name = countryName,
                population = countryInfo.total_population.first().population
            )
        } else {
            null
        }
    }
}