package kirillkitten.shikimori.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model that represents an anime from network.
 */
@JsonClass(generateAdapter = true)
data class RemoteAnime(
    val id: Int,
    val name: String,
    @Json(name = "image") val images: RemoteAnimeImages,
    val kind: String,
    @Json(name = "aired_on") val airedOn: String,
)

@JsonClass(generateAdapter = true)
data class RemoteAnimeImages(
    val preview: String
)
