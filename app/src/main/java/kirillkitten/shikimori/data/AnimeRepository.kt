package kirillkitten.shikimori.data

import kirillkitten.shikimori.ANIME_LIST_MAX_SIZE

interface AnimeRepository {
    suspend fun getAnimes(
        limit: Int = ANIME_LIST_MAX_SIZE,
        order: Anime.Order = Anime.Order.RATING
    ): List<Anime>
}
