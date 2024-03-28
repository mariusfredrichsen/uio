package no.uio.ifi.in2000.mafredri.mapboxtest.ui.map

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import androidx.activity.ComponentActivity.*
import androidx.compose.foundation.layout.Box
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(viewportState: MapViewportState) {

    val context = LocalContext.current



    Column {
        Box(modifier = Modifier) {
            MapboxMap(
                mapViewportState = viewportState,
                mapInitOptionsFactory = { ctx ->
                    MapInitOptions(
                        context = ctx,
                        styleUri = Style.DARK,
                        cameraOptions = CameraOptions.Builder()
                            .center(Point.fromLngLat(0.0, 0.0))
                            .zoom(0.0)
                            .build()
                    )
                }
            )
        }
    }
}