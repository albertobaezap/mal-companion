package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.mvp.MainView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Module to provide the main view that needs to be added with a plus because it's the MainActivity
@Module
class ViewModule(val mainView: MainView) {

   @Provides
   @Singleton
   fun provideMainView(): MainView = mainView

}
