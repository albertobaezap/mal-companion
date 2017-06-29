package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.data.repository.MalDataStore
import com.example.alberto.malcompanion.data.repository.RetrofitMalDataStore
import com.example.alberto.malcompanion.mvp.DetailPresenter
import com.example.alberto.malcompanion.mvp.DetailPresenterImpl
import com.example.alberto.malcompanion.mvp.MainPresenter
import com.example.alberto.malcompanion.mvp.MainPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

   @Provides
   @Singleton
   fun provideMalDataStore(retrofitMalDataStore: RetrofitMalDataStore): MalDataStore = retrofitMalDataStore

   @Provides
   @Singleton
   fun provideMainPresenter(mainPresenter: MainPresenterImpl): MainPresenter = mainPresenter

   @Provides
   @Singleton
   fun provideDetailPresenter(detailPresenter: DetailPresenterImpl): DetailPresenter = detailPresenter
}