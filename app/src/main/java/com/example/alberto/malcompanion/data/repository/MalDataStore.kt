package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeUpdateCallback
import com.example.alberto.malcompanion.model.AnimeInfo
import com.example.alberto.malcompanion.model.AnimeItem
import io.reactivex.Single
import okhttp3.ResponseBody

interface MalDataStore {

   fun searchAnime(searchElement: String): Single<List<AnimeInfo>>

   fun requestMyAnimeList(status: String, user: String): Single<List<AnimeItem>>

   fun updateAnime(animeItem: AnimeItem): Single<ResponseBody>
}