package com.example.alberto.malcompanion.data.repository.callbacks

import com.example.alberto.malcompanion.data.bean.Anime

interface AnimeSearchCallback : BaseCallback {

    fun onAnimeSearchSuccess(searchResult: Anime)
}