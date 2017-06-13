package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.MalApplication
import com.example.alberto.malcompanion.data.bean.mapper.AnimeMapper
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.AnimeListManager
import com.example.alberto.malcompanion.data.repository.ListManager
import com.example.alberto.malcompanion.data.repository.MalDataStore
import com.example.alberto.malcompanion.data.repository.RetrofitMalDataStore
import com.example.alberto.malcompanion.data.repository.util.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: MalApplication) {

    @Provides
    @Singleton
    fun provideMalDataStore(retrofitMalDataStore: RetrofitMalDataStore) : MalDataStore = retrofitMalDataStore

    @Provides
    @Singleton
    fun provideListManager(animeListManager: AnimeListManager) : ListManager = animeListManager

}