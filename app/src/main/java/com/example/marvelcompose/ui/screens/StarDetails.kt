package com.example.marvelcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.data.model.Thumbnail

@Composable
fun StarDetailsShow(starDetails: Star) {
    Column {
        Image(
            painter = rememberImagePainter(starDetails.getPathPicture()),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = starDetails.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h6,
        )
        Text(
            text = starDetails.description,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun StarDetailsRender() {
    MaterialTheme {
        StarDetailsShow(
            starDetails = Star(
                id = 0,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
                Thumbnail(path = "", extension = "")
            )
        )
    }
}