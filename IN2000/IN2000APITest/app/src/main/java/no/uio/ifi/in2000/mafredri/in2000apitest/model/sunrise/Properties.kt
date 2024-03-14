package no.uio.ifi.in2000.mafredri.in2000apitest.model.sunrise

data class Properties(
    val body: String,
    val solarmidnight: Solarmidnight,
    val solarnoon: Solarnoon,
    val sunrise: Sunrise,
    val sunset: Sunset
)