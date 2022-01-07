package kirillkitten.shikimori.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.ANIME_LIST_MAX_SIZE
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimeRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * An [ViewModel] that holds an anime list.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    /**
     * Animes [LiveData]. It fetches items when [MainViewModel] class is instantiated.
     */
    val animes: LiveData<List<Anime>> = liveData {
        try {
            val response = repository.getAnimes(ANIME_LIST_MAX_SIZE)
            Timber.d("Received ${response.size} animes")
            Timber.d("Image url example - ${response.first().imgPreview}")
            emit(response)
        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch animes")
        }
    }
}
