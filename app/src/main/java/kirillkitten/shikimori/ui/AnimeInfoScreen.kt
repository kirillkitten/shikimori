package kirillkitten.shikimori.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

@Composable
fun AnimeInfoScreen(anime: Anime) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(anime.name)
                }
            )
        },
        content = { innerPadding ->
            AnimeInfo(anime, Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun AnimeInfo(anime: Anime, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

    }
}

@Preview
@Composable
fun AnimeInfoScreenPreview() {
    ShikimoriTheme {

    }
}
