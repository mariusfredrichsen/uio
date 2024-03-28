package no.uio.ifi.in2000.mafredri.mapboxtest.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Picture
import android.graphics.drawable.Icon
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.ViewAnnotationAnchor
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotationGroup
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.annotation.AnnotationConfig
import com.mapbox.maps.plugin.annotation.AnnotationSourceOptions
import com.mapbox.maps.plugin.annotation.ClusterOptions
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.attribution.generated.AttributionSettings
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import kotlinx.coroutines.delay
import no.uio.ifi.in2000.mafredri.mapboxtest.R

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen() {

    val context = LocalContext.current

    var style by remember { mutableStateOf(Style.SATELLITE_STREETS) }

    val lon = 10.7
    val lat = 59.9

    val points: List<Point> = listOf(
        Point.fromLngLat(10.0, 59.0),
        Point.fromLngLat(6.7, 60.1),
        Point.fromLngLat(10.3, 59.9),
    )

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(lon, lat))
            pitch(360.0)
            zoom(5.0)
            bearing(100.0)
        }
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MapboxMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f),
                    mapViewportState = mapViewportState,
                    mapInitOptionsFactory = { ctx ->
                        MapInitOptions(
                            context = ctx,
                            styleUri = style,
                        )
                    },
                    attributionSettings = AttributionSettings { enabled = true },
                ) {
                    PointAnnotationGroup(
                        annotations = points.map {
                            PointAnnotationOptions()
                                .withPoint(it)
                                .withIconImage(getBitmapFromImage(context, R.drawable.ic_map_pin))
                        },
                        annotationConfig = AnnotationConfig(
                            annotationSourceOptions = AnnotationSourceOptions(
                                clusterOptions = ClusterOptions(
                                    textColorExpression = Expression.color(Color.YELLOW),
                                    textColor = Color.BLACK,
                                    textSize = 20.0,
                                    circleRadiusExpression = literal(25.0),
                                    colorLevels = listOf(
                                        Pair(100, Color.RED),
                                        Pair(50, Color.BLUE),
                                        Pair(0, Color.GREEN)
                                    )
                                )
                            )
                        ),
                        onClick = {
                            Toast.makeText(
                                context,
                                "Clicked on Point Annotation Cluster: $it",
                                Toast.LENGTH_SHORT
                            ).show()
                            true
                        }
                    )
                    PointAnnotation(
                        point = Point.fromLngLat(10.0, 59.0),
                        iconImageBitmap = getBitmapFromImage(context, R.drawable.ic_map_pin),
                        onClick = {
                            Toast.makeText(
                                context,
                                "Clicked on point: $it",
                                LENGTH_SHORT
                            ).show()
                            true
                        }
                    )
                }
//                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        mapViewportState.flyTo(
                            cameraOptions = cameraOptions {
                                center(Point.fromLngLat(13.403, 52.562))
                                zoom(14.0)
                                pitch(45.0)
                            },
                            MapAnimationOptions.mapAnimationOptions { duration(5000) }
                        )
                    }
                ) {
                    Text(text = "Animate camera with FlyTo")
                }
                Button(
                    onClick = {

                    }
                ) {
                    Text(text = "Switch to darkmode")
                }
            }

        }
    }


    LaunchedEffect(Unit) {
        delay(10)
        mapViewportState.flyTo(
            cameraOptions = cameraOptions {
                center(Point.fromLngLat(lon, lat))
                zoom(10.0)
                pitch(0.0)
                bearing(0.0)
            },
            animationOptions = MapAnimationOptions.mapAnimationOptions { duration(2500) },
        )
    }
}

private fun getBitmapFromImage(ctx: Context, drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(ctx, drawable)
    return Bitmap.createBitmap(db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888)
}