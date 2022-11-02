package com.mungomash.yourtimeback.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleClickableText(linkText: String = "Link", link: String, enableCard: Boolean = true, imageVector: ImageVector?) {
    val uriHandler = LocalUriHandler.current

    if (enableCard) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable {
                    uriHandler.openUri(link)
                },
            elevation = 10.dp
        ) {
            LinkText(linkText = linkText, imageVector)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    uriHandler.openUri(link)
                }
        ) {
            LinkText(linkText = linkText, imageVector)
        }
    }

}

@Composable
fun LinkText(linkText: String, imageVector: ImageVector?) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (imageVector != null) {
                Image(imageVector = imageVector, contentDescription = "")
            }
            Text(
                text = linkText,
                style = TextStyle(color = Color.Blue),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}