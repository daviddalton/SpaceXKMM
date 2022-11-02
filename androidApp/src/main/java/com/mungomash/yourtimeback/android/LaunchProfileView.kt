package com.mungomash.yourtimeback.android

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import coil.compose.rememberImagePainter
import com.mungomash.yourtimeback.response.RocketLaunch
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun LaunchProfileView(launch: RocketLaunch, context: Context) {
    Column(modifier = Modifier.fillMaxHeight()) {
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
                launch.links?.flickrImages?.let {
                    if (it.isNotEmpty()) {
                        BasicGreyDivider()
                        val lazyListState: LazyListState = rememberLazyListState()
                        LazyRow(
                            modifier = Modifier
                            .height(300.dp),
                            state = lazyListState,
                            flingBehavior = rememberSnapperFlingBehavior(lazyListState),
                        ) {
                            items(it) { image ->
                                Image(
                                    painter = rememberImagePainter(image),
                                    contentDescription = "Image Of Launch",
                                    modifier = Modifier
                                        .fillParentMaxSize()
                                )
                            }
                        }
                    }
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
        launch.details?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp, bottom = 8.dp),
                elevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = it)
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable {
                    context.startActivity(
                        Intent(
                            context,
                            LaunchSiteActivity::class.java
                        ).putExtra("LAUNCH_SITE_ID", launch.launchSite.id)
                    )
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
                    text = "Rocket",
                    style = TextStyle(fontSize = 24.sp)
                )
                BasicGreyDivider()
                Text(text = launch.rocket.name)
            }
        }
    }
}