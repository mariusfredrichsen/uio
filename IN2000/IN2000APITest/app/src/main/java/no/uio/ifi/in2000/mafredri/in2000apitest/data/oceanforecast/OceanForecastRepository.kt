package no.uio.ifi.in2000.mafredri.in2000apitest.data.oceanforecast

import no.uio.ifi.in2000.mafredri.in2000apitest.model.oceanforecast.OceanForecastData

interface OceanForecastRepo {
    suspend fun getOceanForecastData(lat: String, lon: String): OceanForecastData?
}

class OceanForecastRepository(
    private val dataSource: OceanForecastDataSource = OceanForecastDataSource()
) : OceanForecastRepo {

    override suspend fun getOceanForecastData(
        lat: String,
        lon: String,
    ): OceanForecastData {
        return dataSource.getOceanForecastData(lat, lon)
    }


}
