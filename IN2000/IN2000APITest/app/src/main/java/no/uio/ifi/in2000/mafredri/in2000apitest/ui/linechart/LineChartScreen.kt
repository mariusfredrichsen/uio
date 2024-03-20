package no.uio.ifi.in2000.mafredri.in2000apitest.ui.linechart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.compose.legend.rememberLegendItem
import com.patrykandpatrick.vico.compose.legend.rememberVerticalLegend
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.values.AxisValueOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.lineSeries
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Composable
fun LineChartScreen(
    oceanForecastViewModel: OceanForecastViewModel = viewModel()
) {
    val timeData = listOf(
        ("2024-03-11T13:00:00" to 0.1f),
        ("2024-03-11T14:00:00" to 0.5f),
        ("2024-03-11T15:00:00" to 0.3f)
    )
    val ocUIState = oceanForecastViewModel.oceanForecastUIState.collectAsState()

    val dates = timeData.associate {
        LocalDateTime.parse(it.first) to it.second
    }
    val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDateTime>>()
    val xToDates = dates.keys.associateBy { it.toEpochSecond(ZoneOffset.UTC).toFloat() }
    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) {

        modelProducer.tryRunTransaction {
            lineSeries {
                repeat(3) {
                    series(x = xToDates.keys, y = dates.values)
                    updateExtras { it[xToDateMapKey] = xToDates }
                }
            }
        }
    }

    val dateTimeFormatter =
        DateTimeFormatter.ofPattern("HH")
    val bottomAxis = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, chartValues, _ ->
        (chartValues.model.extraStore[xToDateMapKey][x] ?: LocalDateTime.ofEpochSecond(
            x.toLong(), 0, ZoneOffset.UTC
        ))
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
                rememberLineCartesianLayer(
                    lines = listOf(rememberLineSpec(shader = DynamicShaders.color(Color(0xff4f42b5)))),
                    axisValueOverrider = AxisValueOverrider.adaptiveYValues(
                        yFraction = 1.2f,
                        round = true
                    )
                ),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = bottomAxis
                ),
                legend = legend
            ),
            modelProducer = modelProducer,
            scrollState = rememberVicoScrollState(),
            zoomState = rememberVicoZoomState(),
            horizontalLayout = HorizontalLayout.FullWidth(),
        )
    }

}