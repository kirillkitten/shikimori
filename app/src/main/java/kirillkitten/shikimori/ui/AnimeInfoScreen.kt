package kirillkitten.shikimori.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
