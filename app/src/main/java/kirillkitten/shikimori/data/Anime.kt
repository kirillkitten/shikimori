package kirillkitten.shikimori.data

import kirillkitten.shikimori.BASE_URL
import kirillkitten.shikimori.data.remote.RemoteAnime

data class Anime(
    val id: Int,
    val name: String,
    val imgPreview: String
)

fun RemoteAnime.toDomainModel(): Anime = Anime(id, name, BASE_URL + images.preview)
