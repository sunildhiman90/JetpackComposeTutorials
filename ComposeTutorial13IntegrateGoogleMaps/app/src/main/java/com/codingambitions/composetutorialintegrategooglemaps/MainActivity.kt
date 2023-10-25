package com.codingambitions.composetutorialintegrategooglemaps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingambitions.composetutorialintegrategooglemaps.ui.theme.ComposeTutorialIntegrateGoogleMapsTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


val indiaState = LatLng(
    20.5937, 78.9629
)

val defaultCameraPosition = CameraPosition.fromLatLngZoom(indiaState, 4f)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialIntegrateGoogleMapsTheme {
                // A surface container using the 'background' color from the theme


                val cameraPositionState = rememberCameraPositionState {
                    position = defaultCameraPosition
                }

                var isMapLoaded by remember {
                    mutableStateOf(false)
                }

                Box(Modifier.fillMaxSize()) {
                    GoogleMapView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp),
                        cameraPositionState = cameraPositionState,
                        onMapLoaded = {
                            isMapLoaded = true
                        }
                    )
                    if (!isMapLoaded) {
                        AnimatedVisibility(visible = !isMapLoaded) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(
                                        Alignment.Center
                                    )
                            )
                        }

                    }

                }

            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit
) {

    val locationState = rememberMarkerState(
        position = indiaState
    )

    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    var showInfoWindow by remember {
        mutableStateOf(true)
    }

    GoogleMap(
        modifier = modifier,
        onMapLoaded = onMapLoaded,
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties
    ) {

        MarkerInfoWindowContent(
            state = locationState,
            draggable = true,
            onClick = {
                if (showInfoWindow) {
                    locationState.showInfoWindow()
                } else {
                    locationState.hideInfoWindow()
                }
                showInfoWindow = !showInfoWindow
                return@MarkerInfoWindowContent false
            },
            title = "India Map Title"
        ) {
            Column {
                Text(text = "CodingAmbitions From India")
                Text(text = "CodingAmbitions From India")
                Text(text = "CodingAmbitions From India")

            }
        }
//        Marker(
//            state = locationState,
//            draggable = true,
//            onClick = {
//                if (showInfoWindow) {
//                    locationState.showInfoWindow()
//                } else {
//                    locationState.hideInfoWindow()
//                }
//                showInfoWindow = !showInfoWindow
//                return@Marker false
//            },
//            title = "India Map Title"
//        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialIntegrateGoogleMapsTheme {

    }
}