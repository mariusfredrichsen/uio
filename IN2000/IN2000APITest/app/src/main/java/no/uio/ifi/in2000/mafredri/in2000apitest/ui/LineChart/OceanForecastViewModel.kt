package no.uio.ifi.in2000.mafredri.in2000apitest.ui.LineChart

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.in2000apitest.data.OceanForecastRepository
import no.uio.ifi.in2000.team7.boatbuddy.model.oceanforecast.OceanForecastData
import no.uio.ifi.in2000.team7.boatbuddy.model.oceanforecast.TimeLocationData

data class OceanForecastUIState(
    val oceanForecastData: OceanForecastData =
        OceanForecastData(
            coordinates = listOf(0.0, 0.0),
            updated_at = "0000-01-01T00:00:00",
            timeseries = listOf(
                TimeLocationData(
                    time = "0000-01-01T00:00:00",
                    sea_surface_wave_from_direction = 0.0,
                    sea_surface_wave_height = 0.0,
                    sea_water_speed = 0.0,
                    sea_water_temperature = 0.0,
                    sea_water_to_direction = 0.0
                )
            )
        )
)

class OceanForecastViewModel : ViewModel() {
    private val repository: OceanForecastRepository = OceanForecastRepository()

    private val _oceanForecastUIState = MutableStateFlow(OceanForecastUIState())
    val oceanForecastUIState: StateFlow<OceanForecastUIState> = _oceanForecastUIState.asStateFlow()

    private var initialized = false
    private var lastPosDate = ""

    @MainThread
    fun initialize(lat: String, lon: String) {
        // checks if the last initialize call has identical data
        initialized = lastPosDate == lat + lon

        if (initialized) return

        initialized = true
        lastPosDate = lat + lon

        loadOceanForecastData(lat, lon)
    }

    private fun loadOceanForecastData(lat: String, lon: String) {
        Log.i("ASDASD", "ASDASDASDASD")
        viewModelScope.launch(Dispatchers.IO) {
            val oceanForecastData = repository.getOceanForecastData(lat, lon)
            _oceanForecastUIState.update {
                it.copy(
                    oceanForecastData = oceanForecastData
                )
            }
        }
    }
}