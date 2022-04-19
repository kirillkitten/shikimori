package kirillkitten.shikimori.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import java.time.LocalDate

@Composable
fun AnimeInfoScreen(viewModel: AnimeInfoViewModel) {
    val anime by viewModel.anime.observeAsState()
    if (anime != null) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(anime!!.name)
                })
            },
            content = { innerPadding ->
                AnimeInfo(anime!!, Modifier.padding(innerPadding))
            }
        )
    }
}

@Composable
fun AnimeInfo(anime: Anime, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = anime.name)
    }
}

@Preview
@Composable
fun AnimeInfoScreenPreview() {
    ShikimoriTheme {
        AnimeInfo(
            anime = Anime(
                id = 1,
                name = "Тетрадь Смерти",
                imgPreview = "",
                airDate = LocalDate.parse("2017-01-01"),
                format = Anime.Format.TV,
                score = 8.0f,
                status = Anime.Status.RELEASED,
            )
        )
    }
}
