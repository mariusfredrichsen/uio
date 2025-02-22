package no.uio.ifi.in2000.mafredri_oblig2.ui.party

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri_oblig2.data.alpaca.AlpacaPartiesRepository

class PartyScreenViewModel: ViewModel() {
    private val _alpacaRepository = AlpacaPartiesRepository()

    private val _partyScreenUIState = MutableStateFlow(PartyScreenUIState())
    val partyScreenUIState = _partyScreenUIState.asStateFlow()

    fun getParty(partyId: String) {
        viewModelScope.launch {
            val party = _alpacaRepository.getParty(partyId = partyId)
            _partyScreenUIState.update {
                it.copy(
                    party = party
                )
            }
        }
    }


}