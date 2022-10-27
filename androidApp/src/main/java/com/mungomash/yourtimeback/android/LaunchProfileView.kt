package com.mungomash.yourtimeback.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungomash.yourtimeback.response.OneLaunchResponse

@Composable
fun LaunchProfileView(launch: OneLaunchResponse) {
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
                Text(
                    text = launch.flightNumber.toString(),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}