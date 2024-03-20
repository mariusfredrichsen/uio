package no.uio.ifi.in2000.mafredri.in2000apitest.model.metalerts

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String,
    val `when`: When
)