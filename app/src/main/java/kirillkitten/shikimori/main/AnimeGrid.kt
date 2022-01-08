package kirillkitten.shikimori.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import kirillkitten.shikimori.ANIME_CARD_ASPECT_RATIO
import kirillkitten.shikimori.ANIME_CARD_MIN_WIDTH
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import timber.log.Timber
import java.time.LocalDate

/**
 * Displays a grid of [animes].
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeGrid(animes: List<Anime>) {
    Timber.i("AnimeGrid is called")
    Timber.i("Anime list size - ${animes.size}")
    LazyVerticalGrid(cells = GridCells.Adaptive(ANIME_CARD_MIN_WIDTH.dp)) {
        items(animes) { anime ->
            AnimeCard(anime)
        }
    }
}

/**
 * Displays a grid of [animes].
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeGrid(pagingItems: LazyPagingItems<Anime>) {
    Timber.i("AnimeGrid is called")
    LazyVerticalGrid(cells = GridCells.Adaptive(ANIME_CARD_MIN_WIDTH.dp)) {
        items(pagingItems) { anime ->
            if (anime != null) AnimeCard(anime) // TODO Show a placeholder when the anime is null
        }
    }
}

@ExperimentalFoundationApi
private fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(items.itemCount) { index ->
        itemContent(items[index])
    }
}

@Composable
private fun AnimeCard(anime: Anime) {
    Column(
        modifier = Modifier
            .clickable { /* TODO Open anime item */ }
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = anime.imgPreview),
            contentDescription = "", // TODO Add content description
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ANIME_CARD_ASPECT_RATIO)
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
                text = anime.format.toString(), // TODO Extract format types from resources
                style = MaterialTheme.typography.caption
            )
            Text(
                text = anime.airDate.year.toString(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimeGridPreview() {
    Timber.i("AnimeGridPreview is called")
    ShikimoriTheme {
        AnimeGrid(fakeAnimes)
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimeCardPreview() {
    Timber.i("AnimeCardPreview is called")
    ShikimoriTheme {
        AnimeCard(fakeAnimes.first())
    }
}

private val fakeAnimes: List<Anime>
    get() {
        Timber.i("Initializing fake animes")
        return listOf(
            Anime(
                id = 1,
                name = "Тетрадь Смерти",
                imgPreview = "",
                format = Anime.Format.TV,
                airDate = LocalDate.parse("2017-01-01")
            ),
            Anime(
                id = 2,
                name = "Евангелион",
                imgPreview = "",
                format = Anime.Format.OVA,
                airDate = LocalDate.parse("2020-01-01")
            ),
            Anime(
                id = 3,
                name = "Токийский Гуль",
                imgPreview = "",
                format = Anime.Format.MOVIE,
                airDate = LocalDate.parse("1996-01-01")
            ),
            Anime(
                id = 4,
                name = "Этот глупый свин не понимает мечту девочки-зайки",
                imgPreview = "",
                format = Anime.Format.TV,
                airDate = LocalDate.parse("2015-01-01")
            ),
            Anime(
                id = 5,
                name = "Когда плачут цикады",
                imgPreview = "",
                format = Anime.Format.TV,
                airDate = LocalDate.parse("2003-01-01")
            ),
            Anime(
                id = 6,
                name = "Атака Титанов",
                imgPreview = "",
                format = Anime.Format.TV,
                airDate = LocalDate.parse("1999-01-01")
            )
        )
    }
