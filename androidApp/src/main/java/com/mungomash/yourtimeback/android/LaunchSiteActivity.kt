package com.mungomash.yourtimeback.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mungomash.yourtimeback.SpaceX
import com.mungomash.yourtimeback.android.ui.theme.YourTimeBackTheme
import com.mungomash.yourtimeback.response.SingleLaunchSite
import kotlinx.coroutines.launch

class LaunchSiteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val launchSiteId = intent.extras?.getString("LAUNCH_SITE_ID")
        setContent {
            YourTimeBackTheme {
                Column {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val scope = rememberCoroutineScope()
                        var launchSite by remember { mutableStateOf<SingleLaunchSite?>(null) }
                        LaunchedEffect(true) {
                            scope.launch {
                                if (launchSiteId != null) {
                                    launchSite = SpaceX().getLaunchSite(launchSiteId)
                                }
                            }
                        }
                        launchSite?.let { LaunchSiteView(it) }
                    }
                }
            }
        }
    }
}

