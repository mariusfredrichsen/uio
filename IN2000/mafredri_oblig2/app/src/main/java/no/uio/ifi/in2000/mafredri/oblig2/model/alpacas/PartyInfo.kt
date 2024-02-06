package no.uio.ifi.in2000.mafredri.oblig2.model.alpacas

import kotlinx.serialization.Serializable

@Serializable
data class PartyInfo(
    val id: String,
    val name: String,
    val leader: String,
    val img: String,
    val color: String,
    val description: String
) {
    override fun toString() = "id: $id\nname: $name\nleader: $leader"
}
// eventuelt legge til en to string for å gjøre det lett