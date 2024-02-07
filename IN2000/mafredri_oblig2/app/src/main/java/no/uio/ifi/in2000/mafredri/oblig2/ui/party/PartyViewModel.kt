package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.ui.AlpacaPartiesUIState


class PartyViewModel: ViewModel() {
    private val alpacaPartiesRepository = AlpacaPartiesRepository()

    val alpacaPartiesUIState: StateFlow<AlpacaPartiesUIState> = alpacaPartiesRepository.loadPartiesInfo()
        .map { AlpacaPartiesUIState(parties = it) }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlpacaPartiesUIState()
        )

    fun getPartyInfo(id: String) {
        viewModelScope.launch {
            alpacaPartiesRepository.getPartyInfo(id)
        }
    }

    init {
        viewModelScope.launch {
            alpacaPartiesRepository.initiateParty()
        }
    }
}