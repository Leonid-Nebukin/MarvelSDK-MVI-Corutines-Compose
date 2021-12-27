package com.example.marvelcompose.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.data.model.Thumbnail
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.utils.extension.toJson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow

@Composable
fun StarListShow(characterList: Flow<PagingData<Star>>,
                 context: Activity,
                 navController: NavHostController,
                 moshi: Moshi) {
    val starLazyList: LazyPagingItems<Star> = characterList.collectAsLazyPagingItems()
    LazyColumn {
        items(starLazyList) { item ->
            item?.let {
                StarsRow(
                    star = item,
                    onStarClick = {
                        navController.navigate("user-details/user={user}".replace("{user}",
                            moshi.toJson(Star::class.java, it)
                        ))
                    })
            }
        }
        starLazyList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.append is LoadState.Error -> {
                    Toast.makeText(
                        context,
                        loadState.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
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
        Image(
            painter = rememberImagePainter(star.getPathPicture()),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = star.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelComposeTheme {
        StarsRow(
            Star(
                id = 0,
                name = "LEOPOLD",
                description = "programmer",
                Thumbnail(
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/9/b0/52d729bb9b84b",
                    extension = "jpg"
                )
            ),
            onStarClick = { }
        )
    }
}