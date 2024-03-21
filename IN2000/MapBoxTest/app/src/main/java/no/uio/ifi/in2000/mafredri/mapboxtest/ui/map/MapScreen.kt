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

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(mapView: MapView) {

    val context = LocalContext.current

    Column {
        mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(10.7, 59.9))
                .pitch(0.0)
                .zoom(10.0)
                .bearing(0.0)
                .build()
        )
    }
}