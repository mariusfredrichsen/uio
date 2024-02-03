package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.serialization.Serializable




@Serializable
data class Pokemons(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonInfo>
)

@Serializable
data class PokemonInfo(
    val name: String,
    val url: String
)

@Serializable
data class PokemonStats(
    val abilities: List<String>,
    val base_experience: Int,
    val forms: List<String>,
    val game_indices: List<String>,
    val height: Int,
    val held_items: List<String>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<String>,
    val name: String,
    val orders: Int,
    val past_abilities: List<String>,
    val past_types: List<String>,
    val species: List<String>,
    val sprites: List<String>,
    val stats: List<String>,
    val types: List<String>,
    val weight: Int
) {

}