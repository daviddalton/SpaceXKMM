package com.mungomash.yourtimeback.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mungomash.yourtimeback.response.RocketLaunch
import com.mungomash.yourtimeback.SpaceX
import com.mungomash.yourtimeback.android.ui.theme.YourTimeBackTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourTimeBackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        val scope = rememberCoroutineScope()
                        var rocketLaunches by remember { mutableStateOf<List<RocketLaunch>>(emptyList()) }
                        LaunchedEffect(true) {
                            scope.launch {
                                rocketLaunches = SpaceX().getRocketLaunches("desc")
                            }
                        }
                        RocketLaunches(rocketLaunches)
                    }
                }
            }
        }
    }
}

@Composable
fun RocketLaunches(launches: List<RocketLaunch>) {
    if (launches.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(launches) { launch ->
                LaunchListView(launch, LocalContext.current)
            }
        }
    }
}
