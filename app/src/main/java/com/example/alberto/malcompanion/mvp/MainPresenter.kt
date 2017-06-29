package com.example.alberto.malcompanion.mvp

interface MainPresenter : BasePresenter<MainView> {

   fun requestMyAnimeList(user: String, status: Int? = null)
}