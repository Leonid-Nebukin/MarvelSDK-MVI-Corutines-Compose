package com.example.marvelcompose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelcompose.data.model.Star
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

                Scaffold {
                    NavHost(
                        navController = navController,
                        startDestination = MainActivityScreens.List.route
                    ) {
                        composable(MainActivityScreens.List.route) {
                            StarListShow(
                                mainViewModel.characterList,
                                this@MainActivity,
                                navController,
                                moshi
                            )
                        }
                        composable(MainActivityScreens.Details.route) { backStackEntry ->
                            val userJson =  backStackEntry.arguments?.getString("user") ?: ""
                            val starObject = moshi.fromJson(Star::class.java, userJson)

                            if (starObject != null) {
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
    object Details : MainActivityScreens("user-details/user={user}")
}