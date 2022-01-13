package kirillkitten.shikimori.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.SearchQuery
import kirillkitten.shikimori.data.paging.PagingFlowFactory
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
    private val pagingFlowFactory: PagingFlowFactory
) : ViewModel() {

    private val queryFlow: Flow<SearchQuery> = savedStateHandle
        .getLiveData(SEARCH_QUERY_KEY, SearchQuery.Default)
        .asFlow()

    /**
     * Anime [Flow] that is exposed by [Pager] and could be observable from the UI.
     * It is aware of the search query, any query changes invalidate flow data.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val animePagingFlow: Flow<PagingData<Anime>> = queryFlow
        .flatMapLatest(pagingFlowFactory::create)
        .cachedIn(viewModelScope)

    /**
     * Handles [newQuery] and replaces current query parameters
     * triggering list flow to emit new elements.
     */
    fun updateQuery(newQuery: SearchQuery) {
        savedStateHandle.set(SEARCH_QUERY_KEY, newQuery)
    }

    companion object {
        private const val SEARCH_QUERY_KEY = "search_query"
    }
}
