package kirillkitten.shikimori.data

import kirillkitten.shikimori.data.remote.AnimeJson
import java.time.LocalDate

/**
 * Model that represents an anime across the app domain.
 */
data class Anime(
    val id: Int,
    val name: String,
    val imgPreview: String,
    val airDate: LocalDate,
    val format: Format,
    val score: Float,
    val status: Status,
) {

    /**
     * Anime release format.
     */
    enum class Format(
        /** Name that is used for network and database queries. */
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
             * @param json must be one of: "tv", "movie", "ova", "ona", "special" or "music"
             */
            fun fromJson(json: String): Format = values()
                .find { it.jsonName == json }
                ?: throw IllegalArgumentException("No Anime.Format with given name - $json")
        }
    }

    /**
     * Anime release status.
     */
    enum class Status(
        /** Name that is used for network and database queries. */
        val jsonName: String
    ) {
        ANNOUNCED("anons"),
        ONGOING("ongoing"),
        RELEASED("released");

        companion object {
            /**
             * Maps [json] name to [Status] constant.
             * Throws [IllegalArgumentException] if there is no suitable enum.
             * @param json must be one of: "anons", "ongoing" or "released"
             */
            fun fromJson(json: String): Status = values()
                .find { it.jsonName == json }
                ?: throw IllegalArgumentException("No Anime.Status with given name - $json")
        }
    }
}

/**
 * Maps [AnimeJson] to its [domain][Anime] model.
 */
fun AnimeJson.toDomainModel(): Anime = Anime(
    id = id,
    name = name,
    imgPreview = images.previewUrl,
    airDate = LocalDate.parse(airDate),
    format = Anime.Format.fromJson(format),
    score = score,
    status = Anime.Status.fromJson(status)
)
