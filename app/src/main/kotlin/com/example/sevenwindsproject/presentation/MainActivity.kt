package com.example.sevenwindsproject.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.R
import com.example.sevenwindsproject.navigation.SevenWindsScreenFlow
import com.example.sevenwindsproject.ui.theme.Background
import com.example.sevenwindsproject.ui.theme.FieldIndicatorColor
import com.example.sevenwindsproject.ui.theme.SevenWindsProjectTheme
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var currentLocation = Pair(0.0, 0.0)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            KoinContext {
                checkLocationPermission()

                SevenWindsProjectTheme {
                    val navController = rememberNavController()

                    var mutableTopBarState by remember { mutableStateOf("") }
                    var isIconButtonActive by remember { mutableStateOf(false) }
                    var route by remember { mutableStateOf("") }

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            topBar = {
                                CenterAlignedTopAppBar(
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = Background
                                    ),
                                    title = {
                                        Text(
                                            text = mutableTopBarState,
                                            style = SfUiDisplayNormal18.copy(color = FieldIndicatorColor)
                                        )
                                    },
                                    navigationIcon = {
                                        if (isIconButtonActive) {
                                            Icon(
                                                modifier = Modifier.clickable {
                                                    navController.navigate(route) {
                                                        popUpTo(0)
                                                    }
                                                },
                                                painter = painterResource(id = R.drawable.arrow_back),
                                                tint = Color.Unspecified,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                )
                            }
                        ) { paddings ->
                            SevenWindsScreenFlow(
                                paddingValues = paddings,
                                currentLocation = LatLng(123.0, -10.0),
                                navHostController = navController,
                                toolBarName = { toolBarName ->
                                    mutableTopBarState = toolBarName
                                },
                                routeToPreviousScreen = { destination ->
                                    route = destination
                                },
                                isIconButtonActive = {
                                    isIconButtonActive = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 100
            )
        }
        val task = fusedLocationProviderClient.lastLocation
    }

    private fun getCurrentLocation(location: Task<Location>): LatLng? {
        var point: LatLng? = null

        location.addOnSuccessListener {
            point = LatLng(it.latitude, it.longitude)
        }

        return point
    }
}
