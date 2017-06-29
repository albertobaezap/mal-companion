package com.example.alberto.malcompanion.mvp

import com.example.alberto.malcompanion.model.AnimeItem

interface DetailPresenter : BasePresenter<DetailView> {

   fun setAnimeItem(animeItem: AnimeItem)

   fun requestAnimeInfo()

   fun loadAnimeUserDetail()

   fun requestEpisodeCountUpdate(updateType: DetailPresenterImpl.Companion.UpdateType)

}