package kirillkitten.shikimori.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kirillkitten.shikimori.data.ANIME_PAGE_SIZE
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.paging.AnimePagingSource
import kirillkitten.shikimori.data.paging.AnimePagingFlowFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object AnimePagingModule {

    /**
     * Anime flow factory instantiated with given [pagingConfig] and [repository] reference.
     * @see AnimePagingFlowFactory.create
     */
    @ActivityRetainedScoped
    @Provides
    fun providePagingFlowFactory(
        repository: AnimeRepository,
        pagingConfig: PagingConfig
    ): AnimePagingFlowFactory = AnimePagingFlowFactory { query ->
        Pager(pagingConfig) {
            AnimePagingSource(repository, query)
        }.flow
    }

    /**
     * Default [PagingConfig].
     * @see PagingConfig
     */
    @ActivityRetainedScoped
    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = ANIME_PAGE_SIZE)
}
