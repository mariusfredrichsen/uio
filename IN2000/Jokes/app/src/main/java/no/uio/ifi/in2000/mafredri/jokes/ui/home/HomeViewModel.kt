package no.uio.ifi.in2000.mafredri.jokes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.jokes.data.JokesRepository
import no.uio.ifi.in2000.mafredri.jokes.model.Joke


class HomeViewModel: ViewModel() {
    val jokesRepository = JokesRepository()

    val jokesUIState = jokesRepository.loadJokes()
        .map { JokesUIState(jokes = it) }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = JokesUIState()
        )


    init {
        viewModelScope.launch {
            jokesRepository.fetchJokes()
        }
    }


}