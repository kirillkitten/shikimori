package kirillkitten.shikimori.data

interface AnimeRepository {
    suspend fun getAnimes(limit: Int): List<Anime>
}
