package kirillkitten.shikimori.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimePagingSource
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.SearchQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * [ViewModel] that holds the state of [Anime] list.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AnimeRepository,
) : ViewModel() {

    /**
     * Anime [Flow] that is exposed by [Pager] and could be observable from the UI.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val animePagingFlow: Flow<PagingData<Anime>> = savedStateHandle
        .getLiveData(SEARCH_QUERY_KEY, SearchQuery.Default)
        .asFlow()
        .flatMapLatest(::animePagingFlowOf)
        .cachedIn(viewModelScope)

    /**
     * Establish a new flow of paging animes with given search [query].
     */
    private fun animePagingFlowOf(query: SearchQuery): Flow<PagingData<Anime>> =
        Pager(PagingConfig(pageSize = 50)) {
            AnimePagingSource(repository, query)
        }.flow

    /**
     * Handles [newQuery] and replaces current query parameters
     * triggering list flow to emit new elements.
     */
    fun onQueryUpdated(newQuery: SearchQuery) {
        savedStateHandle.set(SEARCH_QUERY_KEY, newQuery)
    }

    companion object {
        private const val SEARCH_QUERY_KEY = "search_query"
    }
}
