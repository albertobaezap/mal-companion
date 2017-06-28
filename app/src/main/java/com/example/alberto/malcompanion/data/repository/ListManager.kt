package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.AnimeListEntry
import com.example.alberto.malcompanion.model.AnimeItem
import io.reactivex.Single
import okhttp3.ResponseBody

interface ListManager {

    fun searchAnime(searchTerm: String) : Single<Anime>
    fun requestMyAnimeList(user: String) : Single<List<AnimeListEntry>>
    fun updateAnime(animeItem: AnimeItem) : Single<ResponseBody>
}