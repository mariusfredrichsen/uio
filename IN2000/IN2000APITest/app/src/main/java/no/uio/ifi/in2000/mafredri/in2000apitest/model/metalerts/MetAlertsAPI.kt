package no.uio.ifi.in2000.mafredri.in2000apitest.model.metalerts

data class MetAlertsAPI(
    val features: List<Feature>,
    val lang: String,
    val lastChange: String,
    val type: String
)