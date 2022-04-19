package kirillkitten.shikimori.data

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
     * Provides [Anime] list. Search filters and item order are specified in [query] object.
     * The result is divided into pages, [pageNumber] defines current page.
     */
    suspend fun getAnimes(query: SearchQuery, pageNumber: Int): List<Anime>

    suspend fun getAnime(id: Int): Anime
}

/**
 * [AnimeRepository] default implementation.
 * @see AnimeRepository
 */
class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) : AnimeRepository {

    override suspend fun getAnimes(query: SearchQuery, pageNumber: Int): List<Anime> = with(query) {
        api.getAnimes(
            pageSize = ANIME_PAGE_SIZE,
            pageNumber = pageNumber,
            order = order?.jsonName,
            format = format?.jsonName,
            status = status?.jsonName,
            season = airYear,
            score = score,
        ).map(AnimeJson::toDomainModel)
    }

    override suspend fun getAnime(id: Int): Anime = api.getAnime(id).toDomainModel()
}
