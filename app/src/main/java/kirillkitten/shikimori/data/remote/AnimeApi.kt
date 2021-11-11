package kirillkitten.shikimori.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("api/animes")
    suspend fun getAnimes(@Query("limit") limit: Int): List<RemoteAnime>
}
