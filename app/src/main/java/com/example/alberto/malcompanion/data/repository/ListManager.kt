package com.example.alberto.malcompanion.data.repository

import android.util.Log
import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.AnimeListEntry
import com.example.alberto.malcompanion.data.bean.MyList
import io.reactivex.Observable
import io.reactivex.Single

interface ListManager {

    fun searchAnime(searchTerm: String) : Observable<Anime>
    fun requestMyAnimeList(user: String) : Single<List<AnimeListEntry>>
}