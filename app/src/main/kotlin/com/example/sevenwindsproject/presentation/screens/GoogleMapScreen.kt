package com.example.sevenwindsproject.presentation.screens

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.sevenwindsproject.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapScreen(
    point: LatLng,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(point, 10f)
    }

    val singaporeState = MarkerState(point)

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        MapMarker(
            context = LocalContext.current,
            position = singaporeState.position,
            title = "Your position",
            icon = R.drawable.cafe
        )
//            Marker(
//                state = singaporeState,
//                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
//            )
    }
}

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    @DrawableRes icon: Int
) {
    val icon = bitmapDescriptor(
        context,
        icon
    )

    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon
    )
}

fun bitmapDescriptor(
    context: Context,
    icon: Int
): BitmapDescriptor {
    val drawable = ContextCompat.getDrawable(context, icon)
    drawable!!.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bm)
}

@Preview
@Composable
fun GoogleMapScreenPreview() {
    GoogleMapScreen(
        point = LatLng(0.0, 0.0)
    )
}
