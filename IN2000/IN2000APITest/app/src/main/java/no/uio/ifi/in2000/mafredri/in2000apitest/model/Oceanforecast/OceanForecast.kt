package no.uio.ifi.in2000.mafredri.in2000apitest.model.Oceanforecast

data class OceanForecast(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)