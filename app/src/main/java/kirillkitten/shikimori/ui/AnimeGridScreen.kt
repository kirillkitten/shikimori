package kirillkitten.shikimori.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import kirillkitten.shikimori.R
import kirillkitten.shikimori.ui.components.AnimeGrid

@Composable
fun AnimeGridScreen(
    viewModel: AnimeGridViewModel,
    onAnimeClick: (Int) -> Unit
) {
    val animes = viewModel
        .animePagingFlow
        .collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.app_name))
            })
        },
        content = { innerPadding ->
            AnimeGrid(
                pagingItems = animes,
                onClick = { (id) ->
                    onAnimeClick(id)
                },
                modifier = Modifier.padding(innerPadding),
            )
        }
    )
}
