package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Pokedex {
    @GET("pokemon/tepig")
    suspend fun getPokemonInfo(): String
}

object PokemonApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokedex: Pokedex = retrofit.create(Pokedex::class.java)
}