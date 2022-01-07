package kirillkitten.shikimori.data

import kirillkitten.shikimori.BASE_URL
import kirillkitten.shikimori.data.remote.RemoteAnime
import java.time.LocalDate

/**
 * A model that represents an anime across the app domain.
 */
data class Anime(
    val id: Int,
    val name: String,
    val imgPreview: String,
    val format: Format,
    val airDate: LocalDate,
) {

    /**
     * Anime release format.
     */
    enum class Format {
        TV, MOVIE, OVA, ONA, SPECIAL, MUSIC
    }

    /**
     * Anime sorting order.
     */
    enum class Order {
        ID, RATING, POPULARITY, NAME, AIR_DATE
    }
}

/**
 * Converts [RemoteAnime] anime to its [domain][Anime] model.
 */
fun RemoteAnime.toDomainModel(): Anime = Anime(
    id = id,
    name = name,
    imgPreview = BASE_URL + images.preview,
    format = when (kind) {
        "tv" -> Anime.Format.TV
        "movie" -> Anime.Format.MOVIE
        "ova" -> Anime.Format.OVA
        "ona" -> Anime.Format.ONA
        "special" -> Anime.Format.SPECIAL
        "music" -> Anime.Format.MUSIC
        else -> throw IllegalArgumentException("No enum constant with given name - $kind")
    },
    airDate = LocalDate.parse(airedOn),
)

val Anime.Order.remoteName: String
    get() = when (this) {
        Anime.Order.ID -> "id"
        Anime.Order.RATING -> "ranked"
        Anime.Order.POPULARITY -> "popularity"
        Anime.Order.NAME -> "name"
        Anime.Order.AIR_DATE -> "aired_on"
    }
