package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.oblig2.data.alpacas.AlpacaPartiesRepository

class HomeScreenViewModel: ViewModel() {
    private val alpacaPartiesRepository = AlpacaPartiesRepository()

    val alpacaPartiesUIState: StateFlow<AlpacaPartiesUIState> = combine(
        alpacaPartiesRepository.loadPartiesInfo(),
        alpacaPartiesRepository.loadIsConnected()
    ) { parties, connected -> AlpacaPartiesUIState(
        parties = parties,
        connected = connected
    )
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlpacaPartiesUIState()
        )

    fun initiateParty() {
        viewModelScope.launch {
            alpacaPartiesRepository.initiateParty()
        }
    }

    init {
        viewModelScope.launch {
            alpacaPartiesRepository.isConnectedRep()
        }
    }
}