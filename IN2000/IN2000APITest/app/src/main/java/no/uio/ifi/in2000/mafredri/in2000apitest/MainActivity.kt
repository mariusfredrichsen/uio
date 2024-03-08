package no.uio.ifi.in2000.mafredri.in2000apitest

import android.net.http.HttpResponseCache.install
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.in2000apitest.data.DataSource
import no.uio.ifi.in2000.mafredri.in2000apitest.model.Oceanforecast.OceanForecast
import no.uio.ifi.in2000.mafredri.in2000apitest.ui.LineChart.LineChartScreen
import no.uio.ifi.in2000.mafredri.in2000apitest.ui.theme.IN2000APITestTheme
import java.net.UnknownHostException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IN2000APITestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LineChartScreen()
                }
            }
        }
    }
}