package com.example.alberto.malcompanion

import android.app.Application
import com.example.alberto.malcompanion.dagger.AppComponent
import com.example.alberto.malcompanion.dagger.AppModule
import com.example.alberto.malcompanion.dagger.DaggerAppComponent
import timber.log.Timber

class MalApplication : Application() {

   companion object {
      lateinit var appComponent: AppComponent
   }

   override fun onCreate() {
      super.onCreate()
      Timber.plant(Timber.DebugTree())

      createComponent()

   }

   private fun createComponent() {
      appComponent = DaggerAppComponent.builder()
         .appModule(AppModule())
         .build()
   }

}