package kirillkitten.shikimori.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import kirillkitten.shikimori.ui.components.ANIME_CARD_ASPECT_RATIO
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
                .height(500.dp)
                .aspectRatio(ANIME_CARD_ASPECT_RATIO)
        )
        Row {
            Text(text = "Тип:")
            Spacer(Modifier.width(4.dp))
            Text(text = anime.format.name)
        }
        Row {
            Text(text = "Эпизоды:")
            Spacer(Modifier.width(4.dp))
            Text(text = anime.episodes.toString())
        }
        Row {
            Text(text = "Длительность эпизода:")
            Spacer(Modifier.width(4.dp))
            Text(text = anime.duration.toString())
        }
        if (anime.releaseDate == null) {
            Text(text = "Статус: ${anime.status} c ${anime.airDate.toFormattedString()}")
        } else {
            Text(
                text = "Статус: ${anime.status}" +
                    " c ${anime.airDate.toFormattedString()}" +
                    " по ${anime.releaseDate.toFormattedString()}"
            )
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
