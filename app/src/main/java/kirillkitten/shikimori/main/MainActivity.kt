package kirillkitten.shikimori.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kirillkitten.shikimori.R
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import timber.log.Timber

/**
 * [Activity][android.app.Activity] that displays an anime grid.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    /**
     * @see androidx.activity.ComponentActivity.onCreate
     */
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
        Timber.i("AnimeGridScreenContent is called")
        if (viewModel.isLoading) {
            LoadingScreen()
        } else {
            AnimeGrid(viewModel.animes)
        }
    }

    @Composable
    private fun LoadingScreen() {
        Timber.i("LoadingScreen is called")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
