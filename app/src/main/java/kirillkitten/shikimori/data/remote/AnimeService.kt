package kirillkitten.shikimori.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeService @Inject constructor(private val api: AnimeAPI) {
    suspend fun animes(limit: Int): List<RemoteAnime> = api.animes(limit)
}
