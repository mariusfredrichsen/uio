package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.ui.AlpacaPartiesUIState

class HomeScreenViewModel: ViewModel() {
    private val alpacaPartiesRepository = AlpacaPartiesRepository()

    val alpacaPartiesUIState: StateFlow<AlpacaPartiesUIState> = combine(
        alpacaPartiesRepository.loadPartiesInfo(),
        alpacaPartiesRepository.loadPartiesVotes(),
        alpacaPartiesRepository.loadSelectedDistrict()
    ) {partiesInfo, partiesVotes, selectedDistrict -> AlpacaPartiesUIState(
        partiesInfo = partiesInfo,
        partiesVotes = partiesVotes,
        selectedDistrict = selectedDistrict
    ) }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlpacaPartiesUIState()
        )

    init {
        viewModelScope.launch {
            alpacaPartiesRepository.getPartiesVotes()
            alpacaPartiesRepository.getPartiesInfo()
        }
    }

    fun getPartyVotes(district: District) {
        viewModelScope.launch {
            alpacaPartiesRepository.getPartyVotes(district)
        }
    }

    fun selectDistrict(district: String) {
        viewModelScope.launch {
            alpacaPartiesRepository.selectDistrict(district)
        }
    }
}