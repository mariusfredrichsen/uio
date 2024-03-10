package no.uio.ifi.in2000.team7.boatbuddy.model.oceanforecast

data class OceanForecastAPI(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)