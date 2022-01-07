package kirillkitten.shikimori.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.ANIME_LIST_MAX_SIZE
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimeRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * An [ViewModel] that holds an anime list.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    /**
     * Anime list that is observable
     * from [Composable][androidx.compose.runtime.Composable] functions.
     * @see [mutableStateListOf]
     * @see [SnapshotStateList]
     */
    val animes: SnapshotStateList<Anime> = mutableStateListOf()

    /**
     * [Boolean] flag indicating whether animes are currently downloading
     * from [Repository][AnimeRepository]. It is backed by [MutableState] and thus can be observed
     * from [Composable][androidx.compose.runtime.Composable] functions.
     * @see [mutableStateOf]
     * @see [MutableState]
     * @see [AnimeRepository.getAnimes]
     */
    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        fetchAnimes()
    }

    private fun fetchAnimes() {
        Timber.i("fetchAnimes is called")
        isLoading = true
        animes.clear()
        viewModelScope.launch {
            try {
                Timber.i("Request animes from Repository")
                val response = repository.getAnimes(ANIME_LIST_MAX_SIZE)
                Timber.i("Received ${response.size} animes")
                Timber.i("First received anime = ${response.firstOrNull()}")
                animes.addAll(response)
            } catch (e: Exception) {
                Timber.e(e, "Failed to fetch animes")
            } finally {
                isLoading = false
            }
        }
    }
}
