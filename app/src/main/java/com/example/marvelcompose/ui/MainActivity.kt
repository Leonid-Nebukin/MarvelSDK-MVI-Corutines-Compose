package com.example.marvelcompose.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import com.example.marvelcompose.ui.viewmodel.MainViewModel
import com.example.marvelcompose.utils.Status
import com.example.marvelcompose.utils.extension.withViewModel

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
                            Surface(color = MaterialTheme.colors.background) {
                                Greeting(name = it.data?.copyright ?: "")
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