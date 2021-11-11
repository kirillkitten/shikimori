package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.AnimeService
import kirillkitten.shikimori.data.remote.RemoteAnime
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(private val service: AnimeService) :
    AnimeRepository {
    override suspend fun getAnimeList(limit: Int): List<Anime> {
        return service.animes(10).map(RemoteAnime::toDomainModel)
    }
}
