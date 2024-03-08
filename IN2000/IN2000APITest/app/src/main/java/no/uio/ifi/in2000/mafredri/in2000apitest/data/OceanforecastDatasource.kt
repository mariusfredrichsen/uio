package no.uio.ifi.in2000.mafredri.in2000apitest.data


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.in2000apitest.model.Oceanforecast.OceanForecast

class DataSource(private val path: String = "https://api.met.no/weatherapi/oceanforecast/2.0/complete?lat=59.9&lon=10.7") {

    private val client = HttpClient{
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun hentFeatures(): OceanForecast {
        return runBlocking {
            client.get(path).body<OceanForecast>()
        }
    }
}