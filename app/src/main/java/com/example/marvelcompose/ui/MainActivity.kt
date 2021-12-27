package com.example.marvelcompose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.data.model.Star
import com.example.marvelcompose.ui.MainActivity.Companion.STAR_KEY
import com.example.marvelcompose.ui.screens.SearchView
import com.example.marvelcompose.ui.screens.StarDetailsShow
import com.example.marvelcompose.ui.screens.StarListShow
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.ui.viewmodel.MainViewModel
import com.example.marvelcompose.utils.extension.fromJson
import com.example.marvelcompose.utils.extension.withViewModel
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var moshi: Moshi

    private val mainViewModel by lazy {
        withViewModel<MainViewModel> { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                val navController = rememberNavController()
                val textState = remember {
                    mutableStateOf(TextFieldValue(""))
                }
                with(textState.value.text) {
                    if (this.length >= 3) {
                        mainViewModel.searchPager(this)
                    } else if (this.isEmpty()){
                        mainViewModel.searchPager(null)
                    }
                }

                Scaffold() {
                    NavHost(
                        navController = navController,
                        startDestination = MainActivityScreens.List.route
                    ) {
                        composable(MainActivityScreens.List.route) {
                            Column {
                                SearchView(state = textState)
                                StarListShow(
                                    characterList = mainViewModel.characterList,
                                    navController = navController,
                                    scaffold = rememberScaffoldState(),
                                    coroutineScope = rememberCoroutineScope(),
                                    moshi = moshi
                                )
                            }
                        }
                        composable(MainActivityScreens.Details.route) { backStackEntry ->
                            val userJson = backStackEntry.arguments?.getString(STAR_KEY) ?: ""
                            val starObject = moshi.fromJson(Star::class.java, userJson)

                            starObject?.let {
                                StarDetailsShow(starDetails = starObject)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val STAR_KEY = "STAR_KEY"
    }
}

sealed class MainActivityScreens(val route: String) {
    object List : MainActivityScreens("list")
    object Details : MainActivityScreens("user-details/$STAR_KEY={$STAR_KEY}")
}