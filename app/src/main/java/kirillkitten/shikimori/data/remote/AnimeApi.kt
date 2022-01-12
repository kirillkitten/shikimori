package kirillkitten.shikimori.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL: String = "https://shikimori.one"

/**
 * Network function set for [kirillkitten.shikimori.data.Anime] information.
 */
interface AnimeApi {

    /**
     * Fetch anime list with given parameters.
     * Number of [pageSize] must be an [Int] between 1 and 100000 (inclusive).
     * [pageNumber] must be from 1 to 50 (inclusive)
     * [order] must be one of: "id", "ranked", "popularity", "name" or "aired_on"
     */
    @GET("api/animes")
    suspend fun getAnimes(
        @Query("page") pageNumber: Int?,
        @Query("limit") pageSize: Int?,
        @Query("order") order: String?,
        @Query("kind") format: String?,
        @Query("status") status: String?,
        @Query("season") season: Int?,
        @Query("score") score: Int?,
    ): List<AnimeJson>
}
