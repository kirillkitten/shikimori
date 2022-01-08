package kirillkitten.shikimori.data.remote

import kirillkitten.shikimori.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Network function set for [kirillkitten.shikimori.data.Anime] information.
 */
interface AnimeApi {

    @GET("api/animes")
    suspend fun getAnimes(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String
    ): List<AnimeJson>
}

/**
 * [Retrofit] implementation of the [AnimeApi] interface.
 */
val AnimeApiService: AnimeApi = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(AnimeApi::class.java)
