package no.uio.ifi.in2000.mafredri.in2000apitest.ui.sunrisechart

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.in2000apitest.data.sunrise.SunriseRepository
import no.uio.ifi.in2000.mafredri.in2000apitest.model.sunrise.SunriseData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class SunriseUIState(
    val sunriseDataToday: SunriseData? = null,
    val sunriseDataYesterday: SunriseData? = null
)

class SunriseViewModel : ViewModel() {
    private val repository: SunriseRepository = SunriseRepository()

    private val _sunriseUIState = MutableStateFlow(SunriseUIState())
    val sunriseUIState: StateFlow<SunriseUIState> = _sunriseUIState.asStateFlow()

    private var initialized = false
    private var lastPosDate = ""

    @MainThread
    fun initialize(lat: String, lon: String, date: String = "") {
        // checks if the last initialize call has identical data
        initialized = lastPosDate == lat + lon + date

        if (initialized) return

        initialized = true
        lastPosDate = lat + lon + date
        loadSunriseData(lat, lon, date)
    }

    private fun loadSunriseData(lat: String, lon: String, date: String = "") {
        val currentDateStr: String
        val yesterdayDateStr: String
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        if (date.isBlank()) {
            val currentDate = LocalDateTime.now()
            val yesterdayDate = currentDate.minusDays(1)
            currentDateStr = currentDate.format(format)
            yesterdayDateStr = yesterdayDate.format(format)
        } else {
            val currentDate = LocalDateTime.parse(date)
            val yesterdayDate = currentDate.minusDays(1)
            currentDateStr = currentDate.format(format)
            yesterdayDateStr = yesterdayDate.format(format)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val sunriseDataToday = repository.getSunriseData(lat, lon, currentDateStr)
            val sunriseDataYesterday = repository.getSunriseData(lat, lon, yesterdayDateStr)
            _sunriseUIState.update {
                it.copy(
                    sunriseDataToday = sunriseDataToday,
                    sunriseDataYesterday = sunriseDataYesterday
                )
            }
        }
    }
}