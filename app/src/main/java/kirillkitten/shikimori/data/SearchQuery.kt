package kirillkitten.shikimori.data

import android.os.Parcelable
import androidx.annotation.IntRange
import kotlinx.parcelize.Parcelize

/**
 * Set of anime search query parameters.
 */
@Parcelize
data class SearchQuery(
    val airYear: Int?,
    val format: Anime.Format?,
    val status: Anime.Status?,
    @IntRange(from = 0, to = 10) val score: Int?,
    val order: Order = Order.Default,
) : Parcelable {

    /**
     * Anime sorting order.
     */
    enum class Order(
        /** Name that is used for network and database queries. */
        val jsonName: String
    ) {
        RATING("ranked"),
        POPULARITY("popularity"),
        NAME("name"),
        AIR_DATE("aired_on");

        override fun toString(): String = when (this) {
            RATING -> "По рейтингу"
            POPULARITY -> "По популярности"
            NAME -> "По алфавиту"
            AIR_DATE -> "По дате выхода"
        }

        companion object {

            val Default: Order = RATING

            /**
             * Maps [json] name to [Order] constant.
             * Throws [IllegalArgumentException] if there is no suitable enum.
             * @param json must be one of: "id", "ranked", "popularity", "name" or "aired_on"
             */
            fun fromJson(json: String): Order = values()
                .find { it.jsonName == json }
                ?: throw IllegalArgumentException("No Anime.Order with given name - $json")
        }
    }

    companion object {
        /**
         * Default parameter set. No filter is active. Order is [Order.RATING]
         */
        val Default: SearchQuery = SearchQuery(
            airYear = null,
            format = null,
            status = null,
            score = null,
            order = Order.RATING
        )
    }
}
