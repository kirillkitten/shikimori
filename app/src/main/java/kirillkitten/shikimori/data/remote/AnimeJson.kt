package kirillkitten.shikimori.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model that represents an anime from remote network.
 */
@JsonClass(generateAdapter = true)
data class AnimeJson(
    val id: Int,
    val name: String,
    @Json(name = "image") val images: AnimeJsonImages,
    @Json(name = "kind") val format: String,
    val score: Double,
    val status: String,
    @Json(name = "aired_on") val airDate: String,
    @Json(name = "released_on") val releaseDate: String?,
    val episodes: Int,
    val duration: Int?,
    val description: String?,
)

/**
 * Model containing a set of image urls.
 */
@JsonClass(generateAdapter = true)
data class AnimeJsonImages(

    /** URL endpoint (without the base URL) of the preview image. */
    @Json(name = "preview") val previewEndpoint: String,

    @Json(name = "original") val originalEndpoint: String,
) {
    /** Full URL (containing the base part) of the preview image. */
    val previewUrl: String = BASE_URL + previewEndpoint

    val originalUrl: String = BASE_URL + originalEndpoint
}
