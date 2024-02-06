package no.uio.ifi.in2000.mafredri.oblig2.data.alpacas

import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo

class AlpacaPartiesRepository {
    private val _partiesInfo = MutableStateFlow<List<PartyInfo>>(listOf())


    private val _connected = MutableStateFlow<Boolean>(false)

    fun loadPartiesInfo(): StateFlow<List<PartyInfo>> = _partiesInfo.asStateFlow()

    fun loadIsConnected(): StateFlow<Boolean> = _connected.asStateFlow()

    suspend fun initiateParty() {
        _partiesInfo.update {
            fetchAlpacaData()
        }
    }

    suspend fun isConnectedRep() {
        _connected.update {
            isConnected()
        }
    }
}