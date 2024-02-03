package no.uio.ifi.in2000.mafredri.forelesning3pokemon.network

object PokemonRoutes {
    private const val BASE_URL: String = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0"
    var POKEMON_INFO = "$BASE_URL?limit=100000&offset=0"
}