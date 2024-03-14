package no.uio.ifi.in2000.mafredri.in2000apitest.data.sunrise

import no.uio.ifi.in2000.mafredri.in2000apitest.model.sunrise.SunriseData

interface SunriseRepo {
    suspend fun getSunriseData(lat: String, lon: String, date: String = ""): SunriseData?
}

class SunriseRepository(
    private val dataSource: SunriseDataSource = SunriseDataSource()
) : SunriseRepo {

    override suspend fun getSunriseData(lat: String, lon: String, date: String): SunriseData? {
        return dataSource.getSunriseData(lat, lon, date)
    }


}
