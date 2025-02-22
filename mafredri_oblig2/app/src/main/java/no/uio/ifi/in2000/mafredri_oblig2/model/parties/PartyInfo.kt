package no.uio.ifi.in2000.mafredri_oblig2.model.parties

data class PartyInfo(
    val id: String,
    val name: String,
    val leader: String,
    val img: String,
    val color: String,
    val description: String
)
