package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeUpdateCallback

interface MalDataStore {

    fun searchAnime(searchElement: String, callback: AnimeSearchCallback)

    fun requestMyAnimeList(status: String, user: String, callback: AnimeListCallback)

    fun updateAnime(id: Int, callback: AnimeUpdateCallback)
}