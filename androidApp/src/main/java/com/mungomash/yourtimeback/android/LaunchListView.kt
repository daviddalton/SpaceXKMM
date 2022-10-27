package com.mungomash.yourtimeback.android

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungomash.yourtimeback.response.RocketLaunch

@Composable
fun LaunchListView(launch: RocketLaunch, context: Context) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            Box(modifier = Modifier.clickable {
                context.startActivity(Intent(context, LaunchActivity::class.java).putExtra("FLIGHT_ID", launch.flightNumber))
            }) {
                Column {
                    Text(
                        text = launch.missionName,
                        style = TextStyle(
                            fontSize = 26.sp
                        ),
                        modifier = Modifier.padding(4.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.flight_takeoff_48px), contentDescription = "")
                        Text(text = launch.launchDateUTC)
                        Text(text = " | ")
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.rocket_launch_48px), contentDescription = "")
                        Text(text = launch.flightNumber.toString())
                    }
                }
            }
            BasicGreyDivider()
            launch.links?.webcast?.let {
                SimpleClickableText(it, it)
            }
            BasicGreyDivider()
            launch.links?.article?.let {
                Row {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.newspaper_48px), contentDescription = "")
                    SimpleClickableText(it, it)
                }
            }
            BasicGreyDivider()
            launch.links?.wikipedia?.let {
                SimpleClickableText("Wiki Page", it)
            }
        }
    }
}