package no.uio.ifi.in2000.mafredri.in2000apitest.ui.LineChart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.in2000apitest.data.DataSource
import no.uio.ifi.in2000.mafredri.in2000apitest.model.Oceanforecast.OceanForecast
import java.net.UnknownHostException


data class LineChartUIState(
    val oceanForecast: OceanForecast? = null
)

class LineChartViewModel(): ViewModel() {

    private val _lineChartUIState = MutableStateFlow(LineChartUIState())

    val lineChartUIState: StateFlow<LineChartUIState> = _lineChartUIState.asStateFlow()

    private fun loadOceanData() {
        viewModelScope.launch {
            val datasource = DataSource()
            val data: OceanForecast? = try {
                datasource.hentFeatures()
            } catch (e: UnknownHostException) {
                null
            }
            _lineChartUIState.update {
                it.copy(oceanForecast = data)
            }
        }
    }

}