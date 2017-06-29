package com.example.alberto.malcompanion.mvp

import com.example.alberto.malcompanion.model.AnimeInfo
import com.example.alberto.malcompanion.model.AnimeItem

interface DetailView : BaseView {

   fun showDetailInfo(animeInfo: AnimeInfo)

   fun showAnimeUserDetail(animeItem: AnimeItem)

   fun updateEpisodeCount(count: Int)

}