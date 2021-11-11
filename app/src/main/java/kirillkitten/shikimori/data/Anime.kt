package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.RemoteAnime

data class Anime(
    val id: Int,
    val name: String
)

fun RemoteAnime.toDomainModel(): Anime = Anime(id, name)
