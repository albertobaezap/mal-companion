package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.BuildConfig
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.util.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

   val API_URL = "https://myanimelist.net"

   @Provides
   @Singleton
   fun provideBaseUrl(): String = API_URL


   @Provides
   @Singleton
      //TODO Properties defined in local.properties only for debug mode, remove when login screen is implemented
   fun provideInterceptor(): Interceptor = AuthenticationInterceptor(BuildConfig.USERNAME, BuildConfig.PASSWORD)

   @Provides
   @Singleton
   fun provideClient(interceptor: Interceptor): OkHttpClient {
      return OkHttpClient.Builder()
         .addInterceptor(interceptor)
         .build()
   }

   @Provides
   @Singleton
   fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
      return Retrofit.Builder()
         .baseUrl(baseUrl)
         .addConverterFactory(SimpleXmlConverterFactory.create())
         .client(client)
         .build()
   }

   @Provides
   @Singleton
   fun provideService(retrofit: Retrofit): MyAnimeListApi = retrofit.create(MyAnimeListApi::class.java)

}