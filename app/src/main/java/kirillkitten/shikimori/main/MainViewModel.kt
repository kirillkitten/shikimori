package kirillkitten.shikimori.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.data.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [ViewModel] that holds the state of [Anime] list.
 */
@HiltViewModel
class MainViewModel @Inject constructor(pager: Pager<Int, Anime>) : ViewModel() {

    /**
     * Anime [Flow] that is exposed by [Pager] and could be observable from the UI.
     */
    val animePagingFlow: Flow<PagingData<Anime>> = pager.flow.cachedIn(viewModelScope)
}
