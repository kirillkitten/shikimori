package kirillkitten.shikimori.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeAPI {
    @GET("api/animes")
    suspend fun animes(@Query("limit") limit: Int): List<RemoteAnime>
}
