package com.example.alberto.malcompanion.mvp

interface BasePresenter<T> {

   fun onCreate(view: T)

   fun onResume()

}
