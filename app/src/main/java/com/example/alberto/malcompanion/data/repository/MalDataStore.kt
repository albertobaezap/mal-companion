package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback

interface MalDataStore {

    fun searchAnime(searchElement: String, callback: AnimeSearchCallback)

    fun requestMyAnimeList(status: String, user: String, callback: AnimeListCallback)

}