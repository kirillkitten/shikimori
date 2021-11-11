package kirillkitten.shikimori.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteAnime(
    val id: Int,
    val name: String,
    @Json(name = "image") val images: RemoteAnimeImages
)

@JsonClass(generateAdapter = true)
data class RemoteAnimeImages(
    val preview: String
)
