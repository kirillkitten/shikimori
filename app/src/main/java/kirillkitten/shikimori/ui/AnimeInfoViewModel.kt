package kirillkitten.shikimori.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimeRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AnimeInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AnimeRepository
) : ViewModel() {

    val anime: LiveData<Anime> = savedStateHandle.getLiveData(ANIME_KEY)

    var id: Int?
        get() = savedStateHandle.get<Anime>(ANIME_KEY)?.id
        set(value) {
            if (value != id && value != 0 && value != null) fetchAnimeInfo(value)
        }

    private fun fetchAnimeInfo(id: Int) {
        Timber.d("Request anime with id = $id")
        viewModelScope.launch {
            try {
                val anime = repository.getAnime(id)
                Timber.i("Received anime info: $anime")
                savedStateHandle.set(ANIME_KEY, anime)
            } catch (exception: Exception) {
                Timber.e(exception, "Failed to fetch anime")
            }
        }
    }

    companion object {
        private const val ANIME_KEY = "anime"
    }
}
