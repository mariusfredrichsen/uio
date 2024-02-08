package no.uio.ifi.in2000.mafredri.jokes.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import no.uio.ifi.in2000.mafredri.jokes.model.Joke

class JokesRepository {
    val jokesDatasource = JokesDatasource()

    private val _jokes = MutableStateFlow<List<Joke>>(listOf())
    fun loadJokes(): StateFlow<List<Joke>> = _jokes.asStateFlow()

    suspend fun fetchJokes() {
        jokesDatasource.fetchJokes()
    }

}