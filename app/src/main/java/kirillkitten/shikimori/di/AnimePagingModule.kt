package kirillkitten.shikimori.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kirillkitten.shikimori.data.ANIME_PAGE_SIZE

@Module
@InstallIn(ActivityRetainedComponent::class)
object AnimePagingModule {

    // @ActivityRetainedScoped
    // @Provides
    // fun providePager(
    //     pagingConfig: PagingConfig,
    //     repository: AnimeRepository
    // ): Pager<Int, Anime> = Pager(pagingConfig) {
    //     AnimePagingSource(repository)
    // }

    /**
     * Default anime [PagingConfig].
     */
    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = ANIME_PAGE_SIZE)
}
