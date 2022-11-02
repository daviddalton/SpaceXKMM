package com.mungomash.yourtimeback.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mungomash.yourtimeback.SpaceX
import com.mungomash.yourtimeback.android.ui.theme.YourTimeBackTheme
import com.mungomash.yourtimeback.response.RocketLaunch
import kotlinx.coroutines.launch

class LaunchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flightId = intent.extras?.getInt("FLIGHT_ID")
        setContent {
            YourTimeBackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        val scope = rememberCoroutineScope()
                        var rocketLaunch by remember { mutableStateOf<RocketLaunch?>(null) }
                        LaunchedEffect(true) {
                            scope.launch {
                                if (flightId != null) {
                                    rocketLaunch = SpaceX().getSingleRocketLaunch(flightId)
                                }
                            }
                        }
                        rocketLaunch?.let { LaunchProfileView(launch = it, LocalContext.current) }
                    }
                }
            }
        }
    }
}