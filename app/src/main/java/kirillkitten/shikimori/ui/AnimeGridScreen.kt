package kirillkitten.shikimori.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import kirillkitten.shikimori.R
import kirillkitten.shikimori.ui.components.AnimeGrid

@Composable
fun AnimeGridScreen(viewModel: MainViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.app_name))
            })
        },
        content = { innerPadding ->
            val animes = viewModel.animePagingFlow.collectAsLazyPagingItems()
            AnimeGrid(
                pagingItems = animes,
                onClick = {
                    // TODO Implement anime card click
                },
                modifier = Modifier.padding(innerPadding),
            )
        }
    )
}
