package kirillkitten.shikimori.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kirillkitten.shikimori.ANIME_LIST_MAX_SIZE
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimeRepository
import timber.log.Timber

/**
 * Source of pageable [Anime] data. [repository] is used for serial data requests.
 * @see PagingSource
 */
class AnimePagingSource(private val repository: AnimeRepository) : PagingSource<Int, Anime>() {

    private val pageSize = ANIME_LIST_MAX_SIZE

    /**
     * Attempt to load the next page. Returns either [LoadResult.Page][PagingSource.LoadResult.Page]
     * when the load succeed or [LoadResult.Error][PagingSource.LoadResult.Error] otherwise.
     * @see PagingSource.load
     * @see PagingSource.LoadResult
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val pageNumber = params.key ?: 1
        Timber.i("Try to load $pageNumber page")
        return try {
            val animes = repository.getAnimes(page = pageNumber, limit = pageSize)

            // The next page exists only if the current page contains items at least the limit.
            val nextKey = (pageNumber + 1).takeIf { animes.size >= pageSize }

            LoadResult.Page(data = animes, prevKey = null, nextKey = nextKey)
        } catch (exception: Exception) {
            Timber.e(exception, "Failed to load $pageNumber page")
            LoadResult.Error(exception)
        }
    }

    /**
     * Calculate the page key of the closest page to [state].
     * @see PagingSource.getRefreshKey
     */
    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { (_, prevKey, nextKey) ->
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }
    }
}
