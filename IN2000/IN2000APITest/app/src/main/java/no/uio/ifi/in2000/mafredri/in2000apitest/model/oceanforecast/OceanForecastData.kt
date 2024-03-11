package no.uio.ifi.in2000.team7.boatbuddy.model.oceanforecast

data class OceanForecastData(
    val coordinates: List<Double>,

    val updated_at: String,

    val timeseries: List<TimeLocationData>

)

data class TimeLocationData(
    val time: String,
    val sea_surface_wave_from_direction: Double,
    val sea_surface_wave_height: Double,
    val sea_water_speed: Double,
    val sea_water_temperature: Double,
    val sea_water_to_direction: Double
)


