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
    @Json(name = "image") val images: RemoteAnimeImages,
    @Json(name = "kind") val format: String,
    @Json(name = "aired_on") val airDate: String,
)

@JsonClass(generateAdapter = true)
data class RemoteAnimeImages(
    val preview: String
)