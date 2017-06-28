package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeUpdateCallback
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Store to handle API requests by retrofit calls
 */
class RetrofitMalDataStore @Inject constructor(val myAnimeListApi: MyAnimeListApi) : MalDataStore {

   /**
    * Perform a search with a string parameter
    */
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

   /**
    * Request the user MyAnimeList
    */
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

   /**
    * Update the info for an anime
    */
   override fun updateAnime(id: Int, callback: AnimeUpdateCallback) {

      val url = "https://myanimelist.net/api/animelist/update/$id.xml"
      val strRequest = ""
      val requestBody = RequestBody.create(MediaType.parse("text/plain"), strRequest)
      val updateAnimeRequest = myAnimeListApi.updateAnimeEpisode(url, requestBody)

      updateAnimeRequest.enqueue(object : Callback<ResponseBody> {
         override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            if (response != null && response.isSuccessful) {
               callback.onAnimeUpdateSuccess(response.body())
            }
         }

         override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            if (t != null) {
               callback.onFailure(t)
            }
         }

      })
   }

}