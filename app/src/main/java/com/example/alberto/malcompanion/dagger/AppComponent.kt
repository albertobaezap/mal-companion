package com.example.alberto.malcompanion.dagger

import com.example.alberto.malcompanion.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

//Dagger component
@Singleton
@Component(modules = arrayOf(
        RetrofitModule::class,
        AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}