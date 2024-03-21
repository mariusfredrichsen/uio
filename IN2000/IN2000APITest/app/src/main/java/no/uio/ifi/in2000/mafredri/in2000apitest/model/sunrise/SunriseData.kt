package no.uio.ifi.in2000.mafredri.in2000apitest.model.sunrise

data class SunriseData(
    val coordinates: List<Double>,
    val interval: List<String>, // element 0: start_time, element 1: end_time (Year.Month.Date T Hour.Min.Second)

    val sunriseTime: String,
    val sunriseAzimuth: Double, // which direction the sun rises up from (0degree being north, 90degree being east, etc.)

    val sunsetTime: String,
    val sunsetAzimuth: Double,

    val solarnoonTime: String,
    val solarnoonElevation: Double, // which angle the sun is at its peak from the coordinates
    val solarnoonVisible: Boolean, // tells if the sun is visible at its peak

    val solarmidnightTime: String,
    val solarmidnightElevation: Double,
    val solarmidnightVisible: Boolean, // tells if the sun is not visible at its lowest
)
