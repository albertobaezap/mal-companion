package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.AnimeListEntry
import io.reactivex.Single

interface ListManager {

    fun searchAnime(searchTerm: String) : Single<Anime>
    fun requestMyAnimeList(user: String) : Single<List<AnimeListEntry>>
}