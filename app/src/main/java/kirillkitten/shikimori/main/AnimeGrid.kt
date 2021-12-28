package kirillkitten.shikimori.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

@Composable
fun AnimeCard(anime: Anime) {
    Column(
        modifier = Modifier
            .clickable { /* TODO */ }
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = anime.imgPreview
            ),
            contentDescription = "", // TODO,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Text(
            text = anime.name,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "TV Сериал",
                style = MaterialTheme.typography.caption
            )
            Text(
                text = "2015",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 170, heightDp = 240)
@Composable
fun AnimeCardPreview() {
    ShikimoriTheme {
        AnimeCard(fakeAnimes.first())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeGrid(animes: List<Anime>) {
    LazyVerticalGrid(cells = GridCells.Adaptive(160.dp)) {
        items(animes) { anime ->
            AnimeCard(anime)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeGridPreview() {
    ShikimoriTheme {
        AnimeGrid(fakeAnimes)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, widthDp = 256, heightDp = 256)
@Composable
fun LoadingScreenPreview() {
    ShikimoriTheme {
        LoadingScreen()
    }
}

private val fakeAnimes by lazy {
    listOf(
        Anime(1, "Тетрадь Смерти", ""),
        Anime(2, "Евангелион", ""),
        Anime(3, "Токийский Гуль", ""),
        Anime(4, "Этот глупый свин не понимает мечту девочки-зайки", ""),
        Anime(5, "Когда плачут цикады", ""),
        Anime(6, "Атака Титанов", "")
    )
}
