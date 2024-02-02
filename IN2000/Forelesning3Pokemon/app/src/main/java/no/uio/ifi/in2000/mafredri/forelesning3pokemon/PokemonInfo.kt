package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfo(
    val height: Int,
    val weight: Int,
    val id: Int,
    val ability: String,
    val move: String,
) {

}