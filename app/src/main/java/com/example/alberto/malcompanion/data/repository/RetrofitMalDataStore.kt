package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class RetrofitMalDataStore @Inject constructor(val myAnimeListApi: MyAnimeListApi) : MalDataStore {

   override fun searchAnime(searchElement: String, callback: AnimeSearchCallback) {
      Timber.d("Searching for element " + searchElement)

      val searchAnimeRequest = myAnimeListApi.searchAnime(searchElement)
      searchAnimeRequest.enqueue(object : Callback<Anime> {
         override fun onResponse(call: Call<Anime>?, response: Response<Anime>?) {

            if (response != null && response.isSuccessful) {
               callback.onAnimeSearchSuccess(response.body() as Anime)
            }
         }

         override fun onFailure(call: Call<Anime>?, t: Throwable?) {
            if (t != null) {
               callback.onFailure(t)
            }
         }

      })
   }

   override fun requestMyAnimeList(status: String, user: String, callback: AnimeListCallback) {
      Timber.d("Requesting MyAnimeList for user $user with status $status")

      val animeListRequest = myAnimeListApi.requestMyList(status, user)
      animeListRequest.enqueue(object : Callback<MyList> {

         override fun onResponse(call: Call<MyList>?, response: Response<MyList>?) {
            if (response != null && response.isSuccessful) {
               callback.onAnimeListSuccess(response.body() as MyList)
            }
         }

         override fun onFailure(call: Call<MyList>?, t: Throwable?) {
            if (t != null) {
               callback.onFailure(t)
            }
         }

      })
   }

}