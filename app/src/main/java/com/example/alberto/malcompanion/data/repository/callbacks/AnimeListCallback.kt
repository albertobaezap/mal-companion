package com.example.alberto.malcompanion.data.repository.callbacks

import com.example.alberto.malcompanion.data.bean.MyList

interface AnimeListCallback : BaseCallback {

    fun onAnimeListSuccess(myList: MyList)
}