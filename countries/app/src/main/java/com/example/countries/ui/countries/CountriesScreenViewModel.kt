package com.example.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.CountriesRepository
import com.example.countries.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CountriesScreenViewModel: ViewModel() {
    private val _countriesRepository = CountriesRepository()

    private val _countriesUIState = MutableStateFlow(CountriesUIState())
    val countriesUIState = _countriesUIState.asStateFlow()

    fun addCountry(countryName: String) {
        if (countryName in countriesUIState.value.countries.map { it.name }) return

        viewModelScope.launch(Dispatchers.Main) {
            val country: Country? = _countriesRepository.getCountry(countryName = countryName)

            if (country != null) {
                _countriesUIState.update {
                    it.copy(
                        countries = _countriesUIState.value.countries.plus(country)
                    )
                }
            }
        }
    }

    fun updateText(text: String) {
        viewModelScope.launch {
            _countriesUIState.update {
                it.copy(
                    countryText = text
                )
            }
        }
    }


}