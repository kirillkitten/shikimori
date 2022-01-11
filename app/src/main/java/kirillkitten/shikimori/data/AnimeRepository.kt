package kirillkitten.shikimori.data

import androidx.annotation.IntRange
import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.AnimeJson
import javax.inject.Inject

const val ANIME_PAGE_SIZE: Int = 50

/**
 * Repository that is responsible to provide [Anime] related data.
 * It encapsulates any network or database call.
 * @see AnimeApi
 */
interface AnimeRepository {

    /**
     * Provides [Anime] list with given [limit] and [order]. The result is divided into pages,
     * [page] parameter defines the specific page.
     * @see AnimePagingSource
     */
    suspend fun getAnimes(
        @IntRange(from = 1, to = 100000) page: Int = 1,
        @IntRange(from = 1, to = 50) limit: Int = ANIME_PAGE_SIZE,
        order: Anime.Order = Anime.Order.RATING,
    ): List<Anime>
}

/**
 * [AnimeRepository] default implementation.
 * @see AnimeRepository
 */
class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) : AnimeRepository {

    override suspend fun getAnimes(page: Int, limit: Int, order: Anime.Order): List<Anime> {
        return api.getAnimes(page, limit, order.jsonName).map(AnimeJson::toDomainModel)
    }
}
