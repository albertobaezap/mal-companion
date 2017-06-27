package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.AnimeListEntry
import com.example.alberto.malcompanion.data.bean.MyList
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class AnimeListManager @Inject constructor(val retrofitMalDataStore: RetrofitMalDataStore) : ListManager {

   override fun requestMyAnimeList(user: String): Single<List<AnimeListEntry>> {
      return Single.create { s ->
         retrofitMalDataStore.requestMyAnimeList("all", user, object : AnimeListCallback {
            override fun onAnimeListSuccess(myList: MyList) {
               Timber.d("Response successful: " + myList.animeList.size + " entries found")

               for (entry in myList.animeList) {
                  if (entry.my_status == 1) {
                     Timber.d("Currently watching: " + entry.series_title)
                  }
               }
               s.onSuccess(myList.animeList)
            }

            override fun onFailure(t: Throwable) {
               Timber.e("Response error: " + t.message)
            }

         })
      }
   }

   override fun searchAnime(searchTerm: String): Single<Anime> {
      return Single.create { s ->

         retrofitMalDataStore.searchAnime(searchTerm, object : AnimeSearchCallback {
            override fun onAnimeSearchSuccess(searchResult: Anime) {
               Timber.d("Response successful: " + searchResult.entryList.size + " entries found")

               for (entry in searchResult.entryList) {
                  Timber.d("Anime found: ${entry.title} is ${entry.status}")
               }

               s.onSuccess(searchResult)
            }

            override fun onFailure(t: Throwable) {
               Timber.e("Response error: " + t.message)
            }

         })
      }

   }

}