package no.uio.ifi.in2000.mafredri.in2000apitest.model.oceanforecast

data class Details(
    val sea_surface_wave_from_direction: Double,
    val sea_surface_wave_height: Double,
    val sea_water_speed: Double,
    val sea_water_temperature: Double,
    val sea_water_to_direction: Double
)