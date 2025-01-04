package br.com.lno.android_mvvm_compose.di

import br.com.lno.android_mvvm_compose.data.repository.ContinentsRepositoryImpl
import br.com.lno.android_mvvm_compose.data.repository.CountriesRepositoryImpl
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import br.com.lno.android_mvvm_compose.domain.repository.CountriesRepository
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.normalizedCache
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AppModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApolloClient() = ApolloClient.Builder()
        .serverUrl(serverUrl = "https://countries.trevorblades.com")
        .normalizedCache(normalizedCacheFactory = SqlNormalizedCacheFactory(name = "apollo_cache.db"))
        .build()

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        fun bindContinentsRepository(repository: ContinentsRepositoryImpl): ContinentsRepository

        @Binds
        fun bindCountriesRepository(repository: CountriesRepositoryImpl): CountriesRepository
    }
}