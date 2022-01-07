package kirillkitten.shikimori.data.remote

import kirillkitten.shikimori.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {

    @GET("api/animes")
    suspend fun getAnimes(
        @Query("limit") limit: Int = 50,
        @Query("order") order: String = "ranked"
    ): List<RemoteAnime>
}

val AnimeApiService: AnimeApi = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(AnimeApi::class.java)
