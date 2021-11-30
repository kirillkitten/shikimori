package kirillkitten.shikimori.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShikimoriTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FetchAnimes()
                }
            }
        }
    }
}

@Composable
fun FetchAnimes() {
    val viewModel: MainViewModel = viewModel()
    val animes = viewModel.animes.observeAsState()

    // animes.value?.let { AnimeList(it) }
    animes.value?.let { AnimeGrid(it) }
}

@Composable
fun AnimeList(animes: List<Anime>) {
    LazyColumn {
        items(animes) { AnimeCard(anime = it) }
    }
}

@Composable
fun AnimeCard(anime: Anime) {
    Card {
        Column {
            Image(
                painter = rememberImagePainter(data = anime.imgPreview),
                contentDescription = "", // TODO
                modifier = Modifier.size(128.dp)
            )
            Text(text = anime.name, style = MaterialTheme.typography.h5)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeCardPreview() {
    ShikimoriTheme {
        AnimeCard(Anime(3, "Атака Титанов", ""))
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListPreview() {
    ShikimoriTheme {
        AnimeList(
            animes = listOf(
                Anime(1, "Тетрадь Смерти", ""),
                Anime(2, "Евангелион", ""),
                Anime(3, "Атака Титанов", "")
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeGrid(animes: List<Anime>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(256.dp)
    ) {
        items(animes) { anime ->
            AnimeCard(anime = anime)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeGridPreview() {
    ShikimoriTheme {
        AnimeGrid(
            listOf(
                Anime(1, "Тетрадь Смерти", ""),
                Anime(2, "Евангелион", ""),
                Anime(3, "Атака Титанов", "")
            )
        )
    }
}
