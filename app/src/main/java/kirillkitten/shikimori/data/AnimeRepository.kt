package kirillkitten.shikimori.data

import kirillkitten.shikimori.ANIME_LIST_MAX_SIZE
import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.AnimeJson
import javax.inject.Inject

interface AnimeRepository {
    suspend fun getAnimes(
        limit: Int = ANIME_LIST_MAX_SIZE,
        order: Anime.Order = Anime.Order.RATING
    ): List<Anime>
}

class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) :
    AnimeRepository {

    override suspend fun getAnimes(limit: Int, order: Anime.Order): List<Anime> {
        return api.getAnimes(limit, order.jsonName).map(AnimeJson::toDomainModel)
    }
}
