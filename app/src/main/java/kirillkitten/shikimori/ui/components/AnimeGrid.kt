package kirillkitten.shikimori.ui.components

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
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import timber.log.Timber
import java.time.LocalDate

private const val ANIME_CARD_MIN_WIDTH: Int = 160
private const val ANIME_CARD_ASPECT_RATIO: Float = 225f / 318f

/**
 * Displays an anime grid mapped to [pagingItems].
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeGrid(
    pagingItems: LazyPagingItems<Anime>,
    onClick: (Anime) -> Unit,
    modifier: Modifier
) {
    // TODO Implement generic version of the grid with paging items
    Timber.i("AnimeGrid is called")
    LazyVerticalGrid(cells = GridCells.Adaptive(ANIME_CARD_MIN_WIDTH.dp), modifier = modifier) {
        items(pagingItems) { anime ->
            // TODO Show a placeholder when the anime is null
            if (anime != null) AnimeCard(anime, onClick)
        }
    }
}

@Composable
private fun AnimeCard(anime: Anime, onClick: (Anime) -> Unit) {
    // TODO Use Surface instead of Column
    Column(
        modifier = Modifier
            .clickable { onClick(anime) }
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

/**
 * @see androidx.paging.compose.items
 */
@ExperimentalFoundationApi
private fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(items.itemCount) { index ->
        itemContent(items[index])
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimeCardPreview() {
    ShikimoriTheme {
        AnimeCard(
            Anime(
                id = 1,
                name = "Тетрадь Смерти",
                imgPreview = "",
                airDate = LocalDate.parse("2017-01-01"),
                format = Anime.Format.TV,
                score = 8.0f,
                status = Anime.Status.RELEASED,
            )
        ) {}
    }
}
