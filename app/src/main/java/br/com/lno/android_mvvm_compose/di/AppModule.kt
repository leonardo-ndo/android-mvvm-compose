package br.com.lno.android_mvvm_compose.di

import br.com.lno.android_mvvm_compose.data.repository.ContinentsRepositoryImpl
import br.com.lno.android_mvvm_compose.data.repository.CountriesRepositoryImpl
import br.com.lno.android_mvvm_compose.domain.repository.ContinentsRepository
import br.com.lno.android_mvvm_compose.domain.repository.CountriesRepository
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module(includes = [AppModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://countries.trevorblades.com"

    @Provides
    fun provideRetrofit(): ApolloClient = ApolloClient.Builder()
        .serverUrl(serverUrl = BASE_URL)
        .okHttpClient(okHttpClient = OkHttpClient.Builder().build())
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