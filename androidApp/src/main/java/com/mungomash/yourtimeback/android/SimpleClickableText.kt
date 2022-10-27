package com.mungomash.yourtimeback.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleClickableText(linkText: String = "Link", link: String) {
    val uriHandler = LocalUriHandler.current
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {

        val str = linkText
        val startIndex = str.indexOf("link")
        val endIndex = startIndex + 4
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = startIndex,
            end = endIndex
        )
    }

    ClickableText(
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .fillMaxWidth(),
        style = TextStyle(
            color = Color.Blue,
            fontSize = 16.sp
        )
    )
}