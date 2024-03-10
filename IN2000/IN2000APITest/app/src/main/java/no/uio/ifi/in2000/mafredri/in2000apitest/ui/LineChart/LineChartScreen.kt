package no.uio.ifi.in2000.mafredri.in2000apitest.ui.LineChart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.legend.rememberLegendItem
import com.patrykandpatrick.vico.compose.legend.rememberVerticalLegend
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.lineSeries
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun LineChartScreen(
    oceanForecastViewModel: OceanForecastViewModel = viewModel()
) {
    oceanForecastViewModel.initialize("60.10", "5")
    val ocUIState = oceanForecastViewModel.oceanForecastUIState.collectAsState()

    val dates =
        ocUIState.value.oceanForecastData.timeseries.take(10).associate {
            LocalDate.parse(
                it.time.take(10)
            ) to it.sea_surface_wave_height
        }
    val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDate>>()
    val xToDates = dates.keys.associateBy { it.toEpochDay().toFloat() }
    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) {

        modelProducer.tryRunTransaction {
            lineSeries {
                repeat(3) {
                    series(xToDates.keys, dates.values)
                    updateExtras { it[xToDateMapKey] = xToDates }
                }
            }
        }

    }
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu")
    AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, chartValues, _ ->
        (chartValues.model.extraStore[xToDateMapKey][x] ?: LocalDate.ofEpochDay(x.toLong()))
            .format(dateTimeFormatter)
    }

    val legend = rememberVerticalLegend(
        items = listOf(
            rememberLegendItem(
                icon = rememberShapeComponent(Shapes.pillShape),
                label = rememberTextComponent(),
                labelText = "Bølge høyde"
            )
        ),
        iconSize = 8.dp,
        iconPadding = 8.dp
    )

    Column {
        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
                legend = legend
            ),
            modelProducer = modelProducer,
            scrollState = rememberVicoScrollState(),
            zoomState = rememberVicoZoomState(),
        )
    }

}