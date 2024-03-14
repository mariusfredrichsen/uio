package no.uio.ifi.in2000.mafredri.in2000apitest.ui.wavechart

import android.util.Log
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
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
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
fun OceanForecastScreen(
    oceanForecastViewModel: OceanForecastViewModel = viewModel()
) {
    oceanForecastViewModel.initialize("59", "10")
    val timeData = listOf(
        ("2024-03-11T12:00:00" to 0.1f),
        ("2024-03-11T13:00:00" to 0.1f),
        ("2024-03-11T14:00:00" to 0.5f),
        ("2024-03-11T15:00:00" to 0.3f),
        ("2024-03-11T16:00:00" to 0.3f),
        ("2024-03-11T17:00:00" to 0.1f),
        ("2024-03-11T18:00:00" to 0.1f),
        ("2024-03-11T19:00:00" to 0.5f),
        ("2024-03-11T20:00:00" to 0.3f),
        ("2024-03-11T21:00:00" to 0.3f)
    ).associate { LocalDateTime.parse(it.first) to it.second }
    val ocUIState = oceanForecastViewModel.oceanForecastUIState.collectAsState()

    val dates =
        ocUIState.value.oceanForecastData.timeseries.associate {
            LocalDateTime.parse(
                it.time.subSequence(0, 19)
            ) to it.sea_surface_wave_height.toFloat()
        }
    Log.i("ASDASDA", dates.toString())
    val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDateTime>>()
    val xToDates = dates.keys.associateBy { it.toEpochSecond(ZoneOffset.UTC).toFloat() }
    val modelProducer = remember { CartesianChartModelProducer.build() }

    val dateTimeFormatter =
        DateTimeFormatter.ofPattern("HH:mm d MMM")
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
    if (dates.size > 1) {
        LaunchedEffect(Unit) {
            modelProducer.tryRunTransaction {
                lineSeries {
                    series(x = xToDates.keys, y = dates.values)
                    updateExtras { it[xToDateMapKey] = xToDates }

                }
            }
        }
    }

    Column {


        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(
                    lines = listOf(
                        rememberLineSpec(
                            shader = DynamicShaders.color(
                                Color(
                                    0xFF5C4AF0
                                )
                            )
                        )
                    ),
                    axisValueOverrider = AxisValueOverrider.adaptiveYValues(
                        yFraction = 1.2f,
                        round = true
                    )
                ),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = bottomAxis,
                    guideline = null,
                    itemPlacer =
                    remember {
                        AxisItemPlacer.Horizontal.default(
                            addExtremeLabelPadding = true,
                        )
                    },
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