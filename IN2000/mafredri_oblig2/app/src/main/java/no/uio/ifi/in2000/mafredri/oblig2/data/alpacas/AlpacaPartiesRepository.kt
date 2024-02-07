package no.uio.ifi.in2000.mafredri.oblig2.data.alpacas

import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import java.util.Locale.filter

class AlpacaPartiesRepository {
    private val _partiesInfo = MutableStateFlow<List<PartyInfo>>(listOf())
    fun loadPartiesInfo(): StateFlow<List<PartyInfo>> = _partiesInfo.asStateFlow()

    suspend fun getPartyInfo(id: String) {
        _partiesInfo.update { it ->
            it.filter { it.id == id }
        }
    }
    suspend fun initiateParty() {
        _partiesInfo.update {
            fetchAlpacaData()
        }
    }
}