package no.uio.ifi.in2000.mafredri.in2000apitest.model.oceanforecast

data class OceanForecastAPI(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)