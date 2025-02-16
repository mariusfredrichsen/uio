package no.uio.ifi.in2000.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.countries.data.CountriesRepository
import no.uio.ifi.in2000.countries.model.Country


class CountriesScreenViewModel: ViewModel() {
    private val _countriesRepository = CountriesRepository()

    private val _countriesUIState = MutableStateFlow(CountriesUIState())
    val countriesUIState = _countriesUIState.asStateFlow()

    fun addCountry(countryName: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val country: Country? = _countriesRepository.getCountry(
                countryName = countryName
            )

            if (country != null) {
                _countriesUIState.update { countriesUIState ->
                    countriesUIState.copy(
                        countries = _countriesUIState.value.countries.plus(country)
                    )
                }
            }
        }
    }


}