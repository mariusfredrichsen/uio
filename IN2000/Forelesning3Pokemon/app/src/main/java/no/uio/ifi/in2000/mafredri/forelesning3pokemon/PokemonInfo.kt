package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfo(
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val weight: Int
) {
    override fun toString(): String {
        return "No $order ${name.uppercase()}\nHT $height\nWT $weight "
    }
}