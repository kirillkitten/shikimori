package kirillkitten.shikimori.data

import kirillkitten.shikimori.ANIME_PAGE_MAX_SIZE
import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.AnimeJson
import javax.inject.Inject

interface AnimeRepository {
    suspend fun getAnimes(
        page: Int = 1,
        limit: Int = ANIME_PAGE_MAX_SIZE,
        order: Anime.Order = Anime.Order.RATING
    ): List<Anime>
}

class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) :
    AnimeRepository {

    override suspend fun getAnimes(page: Int, limit: Int, order: Anime.Order): List<Anime> {
        return api.getAnimes(page, limit, order.jsonName).map(AnimeJson::toDomainModel)
    }
}
