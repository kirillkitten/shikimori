package kirillkitten.shikimori.data.paging

import androidx.paging.PagingData
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.SearchQuery
import kotlinx.coroutines.flow.Flow

/**
 * Factory of [Flow]<[PagingData]<[Anime]>>.
 */
fun interface PagingFlowFactory {
    /**
     * Establish a new flow of paging animes with given search [query].
     */
    fun create(query: SearchQuery): Flow<PagingData<Anime>>
}
