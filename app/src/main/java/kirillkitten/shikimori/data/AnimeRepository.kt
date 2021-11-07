package kirillkitten.shikimori.data

interface AnimeRepository {
    suspend fun getAnimeList(limit: Int): List<Anime>
}