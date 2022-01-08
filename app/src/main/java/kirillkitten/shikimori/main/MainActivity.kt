package kirillkitten.shikimori.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import kirillkitten.shikimori.R
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

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
                val animes = viewModel.animePagingFlow.collectAsLazyPagingItems()
                AnimeGrid(pagingItems = animes)
            }
        )
    }
}
