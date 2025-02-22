package no.uio.ifi.in2000.mafredri_oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri_oblig2.data.alpaca.AlpacaPartiesRepository

class HomeScreenViewModel: ViewModel() {
    private val _alpacaPartiesRepository = AlpacaPartiesRepository()

    private val _homeUIState = MutableStateFlow(HomeScreenUIState())
    val homeUIState = _homeUIState.asStateFlow()

    fun updateParties() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUIState.update {
                val parties = _alpacaPartiesRepository.getPartyInfo()
                it.copy(
                    parties = parties
                )
            }
        }
    }

    fun updateVotes(partyId: String) {
        viewModelScope.launch {
            _homeUIState.update {
                val votes = _alpacaPartiesRepository.getVotes(partyId = partyId)
                it.copy(
                    votes = votes
                )
            }
        }
    }

}