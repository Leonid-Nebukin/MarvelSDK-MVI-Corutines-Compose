package com.example.marvelcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.data.model.Thumbnail
import com.example.marvelcompose.ui.MainActivity.Companion.STAR_KEY
import com.example.marvelcompose.ui.MainActivityScreens
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.utils.extension.toJson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun StarListShow(
    characterList: Flow<PagingData<Star>>,
    navController: NavHostController,
    scaffold: ScaffoldState,
    coroutineScope: CoroutineScope,
    moshi: Moshi
) {
    val starLazyList: LazyPagingItems<Star> = characterList.collectAsLazyPagingItems()
    LazyColumn {
        items(starLazyList) { item ->
            item?.let {
                StarsRow(
                    star = item,
                    onStarClick = {
                        navController.navigate(
                            MainActivityScreens.Details.route.replace(
                                "{$STAR_KEY}",
                                moshi.toJson(Star::class.java, it)
                            )
                        )
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
                    coroutineScope.launch {
                        scaffold.snackbarHostState.showSnackbar("error")
                    }
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


@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = { Text(text = "Search stars") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = {
                    state.value =
                        TextFieldValue("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    MaterialTheme {
        SearchView(remember {
            mutableStateOf(TextFieldValue("Spider"))
        })
    }
}