package kirillkitten.shikimori.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kirillkitten.shikimori.ANIME_PAGE_SIZE
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimePagingSource
import kirillkitten.shikimori.data.AnimeRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
object AnimePagingModule {

    @ActivityRetainedScoped
    @Provides
    fun providePager(
        pagingConfig: PagingConfig,
        repository: AnimeRepository
    ): Pager<Int, Anime> = Pager(pagingConfig) {
        AnimePagingSource(repository)
    }

    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = ANIME_PAGE_SIZE)
}
