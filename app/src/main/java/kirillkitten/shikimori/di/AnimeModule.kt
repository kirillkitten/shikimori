package kirillkitten.shikimori.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kirillkitten.shikimori.ANIME_PAGE_SIZE
import kirillkitten.shikimori.BASE_URL
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.data.AnimePagingSource
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.AnimeRepositoryImpl
import kirillkitten.shikimori.data.remote.AnimeApi
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeModule {

    @Suppress("unused")
    @Singleton
    @Binds
    abstract fun bindAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository

    companion object {

        @Singleton
        @Provides
        fun providePager(
            pagingConfig: PagingConfig,
            repository: AnimeRepository
        ): Pager<Int, Anime> = Pager(pagingConfig) {
            AnimePagingSource(repository)
        }

        @Singleton
        @Provides
        fun provideAnimeAPI(converterFactory: Converter.Factory): AnimeApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(AnimeApi::class.java)

        @Provides
        fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

        @Provides
        fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = ANIME_PAGE_SIZE)
    }
}
