package kirillkitten.shikimori.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import timber.log.Timber
import javax.inject.Inject

/**
 * Source of pageable [Anime] data.
 * [repository] is used to serial data requests with specified [query].
 * @see PagingSource
 */
class AnimePagingSource @Inject constructor(
    private val repository: AnimeRepository,
    private val query: SearchQuery
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val pageNumber = params.key ?: 1
        Timber.i("Try to load $pageNumber page")
        return try {
            val animes = repository.getAnimes(query = query, pageNumber = pageNumber)

            // The next page exists only if the current page contains items at least the limit.
            val nextKey = (pageNumber + 1).takeIf { animes.size >= ANIME_PAGE_SIZE }

            LoadResult.Page(data = animes, prevKey = null, nextKey = nextKey)
        } catch (exception: Exception) {
            Timber.e(exception, "Failed to load $pageNumber page")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { (_, prevKey, nextKey) ->
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }
    }
}
