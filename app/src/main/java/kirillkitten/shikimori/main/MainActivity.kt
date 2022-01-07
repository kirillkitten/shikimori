package kirillkitten.shikimori.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import kirillkitten.shikimori.R
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShikimoriTheme {
                AnimeGridScreen()
            }
        }
    }

    @Composable
    private fun AnimeGridScreen() {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = getString(R.string.app_name))
                })
            },
            content = {
                AnimeGridScreenContent()
            }
        )
    }

    @Composable
    private fun AnimeGridScreenContent() {
        val animes: List<Anime> by viewModel.animes.observeAsState(emptyList())
        if (animes.isNotEmpty()) {
            AnimeGrid(animes)
        } else {
            LoadingScreen()
        }
    }
}
