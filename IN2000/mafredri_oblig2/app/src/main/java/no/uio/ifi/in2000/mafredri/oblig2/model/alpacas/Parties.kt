package no.uio.ifi.in2000.mafredri.oblig2.model.alpacas

import kotlinx.serialization.Serializable

@Serializable
data class Parties(val parties: List<PartyInfo>)