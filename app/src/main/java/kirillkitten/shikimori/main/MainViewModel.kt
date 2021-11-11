package kirillkitten.shikimori.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimeRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    var animeList: List<Anime> = emptyList()

    val animes: LiveData<List<Anime>> = liveData {
        try {
            val response = repository.getAnimes(10)
            Timber.d("Received ${response.size} animes")
            emit(response)
        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch animes")
        }
    }

    init {
        viewModelScope.launch {
            try {
                val response = repository.getAnimes(10)
                Timber.d("Received ${response.size} animes")
                animeList = response
            } catch (e: Exception) {
                Timber.e(e, "Failed to fetch animes")
            }
        }
    }

    fun foo() {
        Timber.d("foo function is invoked")
    }
}
