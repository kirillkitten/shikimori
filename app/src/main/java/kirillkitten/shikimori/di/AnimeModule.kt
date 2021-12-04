package kirillkitten.shikimori.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kirillkitten.shikimori.BASE_URL
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.AnimeRepositoryImpl
import kirillkitten.shikimori.data.remote.AnimeApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AnimeModule {

    @Binds
    @Singleton
    abstract fun bindAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository

    companion object {
        @Provides
        @Singleton
        fun provideAnimeAPI(): AnimeApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }
}