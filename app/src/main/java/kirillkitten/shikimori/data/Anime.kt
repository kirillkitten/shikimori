package kirillkitten.shikimori.data

import kirillkitten.shikimori.BASE_URL
import kirillkitten.shikimori.data.remote.AnimeJson
import java.time.LocalDate

/**
 * Model that represents an anime across the app domain.
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
 * Maps [AnimeJson] anime to its [domain][Anime] model.
 */
fun AnimeJson.toDomainModel(): Anime = Anime(
    id = id,
    name = name,
    imgPreview = BASE_URL + images.preview,
    format = fromJson(format),
    airDate = LocalDate.parse(airDate),
)

/**
 * Maps [json] from network response to appropriate [Anime.Format] constant.
 * Throws [IllegalArgumentException] if there is no suitable enum.
 */
private fun fromJson(json: String): Anime.Format = when (json) {
    "tv" -> Anime.Format.TV
    "movie" -> Anime.Format.MOVIE
    "ova" -> Anime.Format.OVA
    "ona" -> Anime.Format.ONA
    "special" -> Anime.Format.SPECIAL
    "music" -> Anime.Format.MUSIC
    else -> throw IllegalArgumentException("No enum constant with given name - $json")
}

/**
 * [Anime.Order] name that is acceptable for network calls.
 * @see AnimeJson
 * @see kirillkitten.shikimori.data.remote.AnimeApi
 */
val Anime.Order.jsonName: String
    get() = when (this) {
        Anime.Order.ID -> "id"
        Anime.Order.RATING -> "ranked"
        Anime.Order.POPULARITY -> "popularity"
        Anime.Order.NAME -> "name"
        Anime.Order.AIR_DATE -> "aired_on"
    }
