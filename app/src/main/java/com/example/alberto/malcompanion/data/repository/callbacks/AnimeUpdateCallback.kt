package com.example.alberto.malcompanion.data.repository.callbacks

import okhttp3.ResponseBody

interface AnimeUpdateCallback : BaseCallback {

   fun onAnimeUpdateSuccess(result: ResponseBody?)
}