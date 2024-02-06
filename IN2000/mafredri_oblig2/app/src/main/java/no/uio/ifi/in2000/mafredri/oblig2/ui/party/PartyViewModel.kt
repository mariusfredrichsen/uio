package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import no.uio.ifi.in2000.mafredri.oblig2.data.alpacas.AlpacaPartiesRepository


class PartyViewModel: ViewModel() {
    private val alpacaPartiesRepository = AlpacaPartiesRepository()

    val alpacaPartyUIState: StateFlow<AlpacaPartyUIState> = alpacaPartiesRepository.getPartyInfo()


}