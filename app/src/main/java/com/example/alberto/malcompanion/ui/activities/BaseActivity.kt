package com.example.alberto.malcompanion.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alberto.malcompanion.MalApplication
import com.example.alberto.malcompanion.dagger.AppComponent

/**
 * Base activity with basic dependency injection to be extended by all other activities.
 */
abstract class BaseActivity : android.support.v7.app.AppCompatActivity() {

    abstract fun injectDependencies(appComponent : com.example.alberto.malcompanion.dagger.AppComponent)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(com.example.alberto.malcompanion.MalApplication.Companion.appComponent)
    }
}