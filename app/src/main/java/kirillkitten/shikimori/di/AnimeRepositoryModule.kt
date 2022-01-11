package kirillkitten.shikimori.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kirillkitten.shikimori.data.AnimeRepository
import kirillkitten.shikimori.data.AnimeRepositoryImpl
import kirillkitten.shikimori.data.remote.AnimeApi
import kirillkitten.shikimori.data.remote.BASE_URL
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeRepositoryModule {

    @Suppress("unused")
    @Singleton
    @Binds
    abstract fun bindAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository

    companion object {

        /**
         * [Retrofit] implementation of the [AnimeApi] interface.
         */
        @Singleton
        @Provides
        fun provideAnimeAPI(converterFactory: Converter.Factory): AnimeApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(AnimeApi::class.java)

        @Provides
        fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()
    }
}