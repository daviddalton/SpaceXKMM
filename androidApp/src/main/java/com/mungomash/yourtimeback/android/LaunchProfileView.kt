package com.mungomash.yourtimeback.android

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungomash.yourtimeback.response.RocketLaunch
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LaunchProfileView(launch: RocketLaunch, context: Context) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = launch.missionName,
                    style = TextStyle(fontSize = 24.sp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val date = OffsetDateTime.parse(launch.launchDateUTC)
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.flight_takeoff_48px), contentDescription = "Takeoff")
                    Text(text = date.format(DateTimeFormatter.RFC_1123_DATE_TIME))
                    Text(text = " | ")
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.rocket_launch_48px), contentDescription = "Rocket Launch")
                    Text(text = launch.flightNumber.toString())
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.rocket), contentDescription = "Rocket")
                    Text(text = launch.rocket.name)
                }
                BasicGreyDivider()
                launch.links?.videoLink?.let {
                    SimpleClickableText("Video", it, false, ImageVector.vectorResource(id = R.drawable.youtube))
                    BasicGreyDivider()
                }

                launch.links?.articleLink?.let {
                    Row {
                        SimpleClickableText("Article", it, false, ImageVector.vectorResource(id = R.drawable.newspaper_48px))
                    }
                    BasicGreyDivider()
                }
                launch.links?.wikipedia?.let {
                    SimpleClickableText("Wiki Page", it, false, ImageVector.vectorResource(id = R.drawable.wikipedia))
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable {
                    context.startActivity(Intent(context, LaunchSiteActivity::class.java).putExtra("LAUNCH_SITE_ID", launch.launchSite.id))
                },
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Launch Site",
                    style = TextStyle(fontSize = 24.sp)
                )
                BasicGreyDivider()
                Text(text = launch.launchSite.nameLong)
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Rocket: " + launch.rocket.name,
                    style = TextStyle(fontSize = 24.sp)
                )
                BasicGreyDivider()
                Text(text = "Type: " + launch.rocket.type)
            }
        }
    }
}