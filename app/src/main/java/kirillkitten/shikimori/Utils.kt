package kirillkitten.shikimori

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toFormattedString(): String = format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
