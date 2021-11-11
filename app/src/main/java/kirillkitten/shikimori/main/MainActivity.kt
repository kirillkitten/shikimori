package kirillkitten.shikimori.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
    animes.value?.let { ShowAnimes(it) }
}

@Composable
fun ShowAnimes(list: List<Anime>) {
    LazyColumn {
        items(list) { AnimeCard(anime = it) }
    }
}

@Composable
fun AnimeCard(anime: Anime) {
    Card {
        Text(text = anime.name, style = MaterialTheme.typography.h5)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimesPreview() {
    ShikimoriTheme {
        ShowAnimes(
            list = listOf(
                Anime(1, "Тетрадь Смерти"),
                Anime(2, "Евангелион"),
                Anime(3, "Атака Титанов")
            )
        )
    }
}
