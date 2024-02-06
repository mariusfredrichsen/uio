package no.uio.ifi.in2000.mafredri.oblig2.ui

import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo

data class AlpacaPartiesUIState(
    val parties: List<PartyInfo> = listOf(),
    val connected: Boolean = false
)