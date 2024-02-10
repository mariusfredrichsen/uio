package no.uio.ifi.in2000.mafredri.jokes.ui.home

import no.uio.ifi.in2000.mafredri.jokes.model.Joke

data class JokesUIState(
    val jokes: List<Joke> = listOf()
)