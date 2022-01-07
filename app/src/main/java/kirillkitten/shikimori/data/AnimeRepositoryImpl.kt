package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.RemoteAnime
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(private val api: AnimeApi) :
    AnimeRepository {

    override suspend fun getAnimes(limit: Int, order: Anime.Order): List<Anime> {
        return api.getAnimes(limit, order.remoteName).map(RemoteAnime::toDomainModel)
    }
}
