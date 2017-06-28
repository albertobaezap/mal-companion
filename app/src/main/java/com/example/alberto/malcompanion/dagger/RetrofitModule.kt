package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.BuildConfig
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.util.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
   fun provideAuthenticationInterceptor(): Interceptor = AuthenticationInterceptor(BuildConfig.USERNAME, BuildConfig.PASSWORD)

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
      val loggingInterceptor = HttpLoggingInterceptor()
      loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      return loggingInterceptor
   }

   @Provides
   @Singleton
   fun provideClient(authenticationInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
      return OkHttpClient.Builder()
         .addInterceptor(authenticationInterceptor)
         .addInterceptor(loggingInterceptor)
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