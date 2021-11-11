package kirillkitten.shikimori.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteAnime(
    val id: Int,
    val name: String
)
