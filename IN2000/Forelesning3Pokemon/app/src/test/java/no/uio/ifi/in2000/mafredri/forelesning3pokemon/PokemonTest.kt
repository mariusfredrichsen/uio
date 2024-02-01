package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import org.junit.Test

class PokemonTest {

    val pokeApi = PokeApiClient()
    val bulbasaur = pokeApi.getPokemonSpecies(1)
    println(bulbasaur)
    @Test
    fun noe() {
        println()
    }
}