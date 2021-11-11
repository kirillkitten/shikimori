package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.RemoteAnime
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) :
    AnimeRepository {

    override suspend fun getAnimes(limit: Int): List<Anime> {
        return api.getAnimes(limit).map(RemoteAnime::toDomainModel)
    }
}
