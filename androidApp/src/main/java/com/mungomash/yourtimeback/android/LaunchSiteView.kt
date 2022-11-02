package com.mungomash.yourtimeback.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungomash.yourtimeback.response.SingleLaunchSite

@Composable
fun LaunchSiteView(launchSite: SingleLaunchSite) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    launchSite.name?.let {
                        Text(
                            text = it,
                            style = TextStyle(fontSize = 24.sp)
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        Alignment.TopEnd
                    ) {
                        launchSite.status?.let {
                            Text(
                                text = it,
                                style = TextStyle(getColorFromStatus(it))
                            )
                        }
                    }
                }
                Row {
                    Text(text = "Launches: ")
                    Text(text = launchSite.successfulLaunches.toString())
                    Text(text = "/")
                    Text(text = launchSite.attemptedLaunches.toString())
                    Text(text = " | ")
                    Text(
                        text = calculateSuccessRate(
                            launchSite.attemptedLaunches,
                            launchSite.successfulLaunches
                        ).toString() + "%"
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                launchSite.details?.let {
                    Text(text = it)
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier
                .padding(8.dp)) {
                Text(
                    text = "Vehicles Launched",
                    style = TextStyle(fontSize = 24.sp)
                )
                BasicGreyDivider()
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    items(launchSite.vehiclesLaunched) { vehicle ->
                        Text(text = vehicle)
                    }

                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Location",
                    style = TextStyle(fontSize = 24.sp)
                )
                BasicGreyDivider()
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = launchSite.location.name)
                    Text(text = " | ")
                    Text(text = launchSite.location.region)
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = launchSite.location.latitude.toString())
                    Text(text = " | ")
                    Text(text = launchSite.location.longitude.toString())
                }
            }
        }
        launchSite.wikiLink?.let {
            SimpleClickableText(linkText = "Wiki Link", link = it, true, ImageVector.vectorResource(id = R.drawable.wikipedia))
        }

    }
}

fun getColorFromStatus(status: String): Color {
    if (status == "active") {
        return Color(40, 150, 45, 255)
    } else if (status == "retired") {
        return Color(233, 0, 0, 255)
    } else if (status == "under construction") {
        return Color(255, 152, 0, 255)
    } else {
        return Color.Black
    }
}

fun calculateSuccessRate(attempted: Int, succeeded: Int): Float {
    return (succeeded.toFloat() / attempted.toFloat()) * 100
}