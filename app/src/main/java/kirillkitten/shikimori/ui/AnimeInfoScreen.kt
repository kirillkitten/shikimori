package kirillkitten.shikimori.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.toFormattedString
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

@Composable
fun AnimeInfoScreen(viewModel: AnimeInfoViewModel) {
    val anime by viewModel.anime.observeAsState()
    anime?.let {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(it.name)
                })
            },
            content = { innerPadding ->
                AnimeInfo(it, Modifier.padding(innerPadding))
            }
        )
    }
}

@Composable
fun AnimeInfo(anime: Anime, modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = rememberImagePainter(data = anime.imgOriginal),
            contentDescription = "", // TODO Add content description
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
        )
        Text(text = "Тип: ${anime.format}")
        Text(text = "Эпизоды: ${anime.episodes}")
        Text(text = "Длительность эпизода: ${anime.duration}")
        Text(
            text = "Статус: ${anime.status} c ${anime.airDate.toFormattedString()}" +
                if (anime.releaseDate != null) {
                    " по ${anime.releaseDate.toFormattedString()}"
                } else {
                    ""
                }
        )
        if (anime.score > 0.0) {
            Text(text = "Рейтинг: ${anime.score}")
        }
        if (anime.description != null) {
            Text(text = anime.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeInfoScreenPreview() {
    ShikimoriTheme {
        AnimeInfo(anime = AnimePreview)
    }
}
