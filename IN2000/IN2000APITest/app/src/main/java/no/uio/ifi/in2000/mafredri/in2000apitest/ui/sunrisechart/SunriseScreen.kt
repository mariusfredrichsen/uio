package no.uio.ifi.in2000.mafredri.in2000apitest.ui.sunrisechart

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.lineSeries
import no.uio.ifi.in2000.mafredri.in2000apitest.model.sunrise.SunriseData
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Composable
fun SunriseScreen(sunriseViewModel: SunriseViewModel = viewModel()) {
    val sunriseUIState = sunriseViewModel.sunriseUIState.collectAsState()

    Log.i("ASDASD", "QIUEWYFGYUWQEGF")

    sunriseViewModel.initialize("59.9", "10.1")


    val modelProducer = remember { CartesianChartModelProducer.build() }


    Column {
        Text(text = "sunrise:       " + sunriseUIState.value.sunriseDataToday?.sunriseTime.toString())
        Text(text = "solarnoon:     " + sunriseUIState.value.sunriseDataToday?.solarnoonTime.toString())
        Text(text = "sunset:        " + sunriseUIState.value.sunriseDataToday?.sunsetTime.toString())
        Text(text = "solarmidnight: " + sunriseUIState.value.sunriseDataToday?.solarmidnightTime.toString())
        Text(text = "")
        Text(text = "sunrise:       " + sunriseUIState.value.sunriseDataYesterday?.sunriseTime.toString())
        Text(text = "solarnoon:     " + sunriseUIState.value.sunriseDataYesterday?.solarnoonTime.toString())
        Text(text = "sunset:        " + sunriseUIState.value.sunriseDataYesterday?.sunsetTime.toString())
        Text(text = "solarmidnight: " + sunriseUIState.value.sunriseDataYesterday?.solarmidnightTime.toString())
        if (sunriseUIState.value.sunriseDataToday != null) {
            val sunriseDataToday: SunriseData = sunriseUIState.value.sunriseDataToday!!
            val sunriseDataYesterday: SunriseData = sunriseUIState.value.sunriseDataYesterday!!

            val data = mapOf(
//                LocalDateTime.parse(sunriseDataYesterday.solarmidnightTime) to sunriseDataYesterday.solarmidnightElevation,
                LocalDateTime.parse(sunriseDataToday.sunriseTime) to 0,
                LocalDateTime.parse(sunriseDataToday.solarnoonTime) to sunriseDataToday.solarnoonElevation,
                LocalDateTime.parse(sunriseDataToday.sunsetTime) to 0,
                LocalDateTime.parse(sunriseDataToday.solarmidnightTime) to sunriseDataToday.solarmidnightElevation,
            )

            val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDateTime>>()
            val xToDates =
                data.keys.associateBy { it.toEpochSecond(ZoneOffset.UTC).toFloat() }
            val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm d MMM")

            val bottomAxis =
                AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, chartValues, _ ->
                    (chartValues.model.extraStore[xToDateMapKey][x] ?: LocalDateTime.ofEpochSecond(
                        x.toLong(), 0, ZoneOffset.UTC
                    ))
                        .format(dateTimeFormatter)
                }
            Log.i("ASDASD", data.toString())

            LaunchedEffect(Unit) {
                modelProducer.tryRunTransaction {
                    lineSeries {
                        series(x = xToDates.keys, y = data.values)
                        updateExtras { it[xToDateMapKey] = xToDates }

                    }
                }
            }


            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberLineCartesianLayer(

                    ),
                    startAxis = rememberStartAxis(
                        itemPlacer = remember { AxisItemPlacer.Vertical.count(count = { 4 }) },
                    ),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = bottomAxis,
                        itemPlacer =
                        remember {
                            AxisItemPlacer.Horizontal.default(
                                addExtremeLabelPadding = true,
                                spacing = 10
                            )
                        },
                    ),
                ),
                modelProducer = modelProducer,
            )
        }
    }
}