package no.uio.ifi.in2000.mafredri_oblig2.ui.home

import no.uio.ifi.in2000.mafredri_oblig2.model.parties.PartyInfo
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes

data class HomeScreenUIState(
    val parties: List<PartyInfo> = listOf(),
    val votes: List<DistrictVotes> = listOf(),
    val selectedDistrict: District = District.ONE
)