package com.example.alberto.malcompanion.mvp

import com.example.alberto.malcompanion.model.AnimeItem

interface MainView : BaseView {

   fun displayList(animeList: List<AnimeItem>)

}