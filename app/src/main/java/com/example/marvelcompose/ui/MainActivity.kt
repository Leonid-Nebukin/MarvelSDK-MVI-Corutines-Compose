package com.example.marvelcompose.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.ui.viewmodel.MainViewModel
import com.example.marvelcompose.utils.Status
import com.example.marvelcompose.utils.extension.withViewModel
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : BaseActivity() {

    private val mainViewModel by lazy {
        withViewModel<MainViewModel> { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getCharacters().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    setContent {
                        MarvelComposeTheme {
                            LazyColumn() {
                                items(it.data?.data?.results ?: listOf()) { item ->
                                    StarsRow(
                                        star = item,
                                        onStarClick = {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "You clicked ${it.name}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        })
                                }
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @Composable
    private fun StarsRow(star: Star, onStarClick: (Star) -> Unit) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onStarClick(star) })
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White)
        ) {

            val path = star.thumbnail.path + "." + star.thumbnail.extension

            GlideImage(
                imageModel = path,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp),
                failure = {
                    Text(text = "image request failed.")
                }
            )

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = star.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelComposeTheme {
        Greeting("Android")
    }
}