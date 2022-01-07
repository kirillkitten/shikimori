package kirillkitten.shikimori.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.AnimeRepositoryImpl
import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.AnimeApiService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AnimeModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(animeApi: AnimeApi): AnimeRepository = AnimeRepositoryImpl(animeApi)

    @Provides
    @Singleton
    fun provideAnimeAPI(): AnimeApi = AnimeApiService
}
