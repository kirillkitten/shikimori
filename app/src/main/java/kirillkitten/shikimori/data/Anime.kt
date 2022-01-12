package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.AnimeJson
import kirillkitten.shikimori.data.remote.BASE_URL
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
    enum class Format(
        /**
         * [String] name that is used for network and database queries.
         */
        val jsonName: String
    ) {
        TV("tv"),
        MOVIE("movie"),
        OVA("ova"),
        ONA("ona"),
        SPECIAL("special"),
        MUSIC("music");

        companion object {
            /**
             * Maps [json] name to [Format] constant.
             * Throws [IllegalArgumentException] if there is no suitable enum.
             * @param json must be one of: "tv", "movie", "ova", "ona", "special", "music"
             */
            fun fromJson(json: String): Format = values()
                .find { it.jsonName == json }
                ?: throw IllegalArgumentException("No Anime.Format with given name - $json")
        }
    }

    /**
     * Anime sorting order.
     */
    enum class Order(
        /**
         * [String] name that is used for network and database queries.
         */
        val jsonName: String
    ) {
        ID("id"),
        RATING("ranked"),
        POPULARITY("popularity"),
        NAME("name"),
        AIR_DATE("aired_on");

        companion object {
            /**
             * Maps [json] name to [Order] constant.
             * Throws [IllegalArgumentException] if there is no suitable enum.
             * @param json must be one of: "id", "ranked", "popularity", "name", "aired_on"
             */
            fun fromJson(json: String): Order = values()
                .find { it.jsonName == json }
                ?: throw IllegalArgumentException("No Anime.Order with given name - $json")
        }
    }
}

/**
 * Maps [AnimeJson] to its [domain][Anime] model.
 */
fun AnimeJson.toDomainModel(): Anime = Anime(
    id = id,
    name = name,
    imgPreview = BASE_URL + images.preview,
    format = Anime.Format.fromJson(format),
    airDate = LocalDate.parse(airDate),
)
