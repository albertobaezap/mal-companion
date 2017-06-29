package com.example.alberto.malcompanion.data.repository

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import com.example.alberto.malcompanion.data.bean.mapper.AnimeMapper
import com.example.alberto.malcompanion.data.bean.mapper.InfoMapper
import com.example.alberto.malcompanion.data.bean.mapper.XmlStringSerializer
import com.example.alberto.malcompanion.data.net.MyAnimeListApi
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeListCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeSearchCallback
import com.example.alberto.malcompanion.data.repository.callbacks.AnimeUpdateCallback
import com.example.alberto.malcompanion.model.AnimeInfo
import com.example.alberto.malcompanion.model.AnimeItem
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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

   var animeMapper: AnimeMapper = AnimeMapper()
   var infoMapper: InfoMapper = InfoMapper()

   override fun requestMyAnimeList(status: String, user: String): Single<List<AnimeItem>> {
      return myAnimeListApi.requestMyList(status, user)
         .subscribeOn(Schedulers.computation())
         .map { s ->
            //Transform from server data to model data
            animeMapper.transform(s.animeList)
               .sortedBy { it.title }
         }
   }

   /**
    * Perform a search with a string parameter
    */
   override fun searchAnime(searchElement: String): Single<List<AnimeInfo>> {
      Timber.d("Searching for element " + searchElement)
      return myAnimeListApi.searchAnime(searchElement)
         .subscribeOn(Schedulers.computation())
         .map { (entryList) -> infoMapper.transform(entryList) }
   }

   /**
    * Update the info for an anime
    */
   override fun updateAnime(animeItem: AnimeItem): Single<ResponseBody> {
      Timber.d("Requesting list update for anime ${animeItem.id}")
      val xmlStringSerializer = XmlStringSerializer()
      val data = xmlStringSerializer.updateAnime(animeItem.watchedEpisodes)
      return myAnimeListApi.updateAnimeEpisode(animeItem.id, data)
         .subscribeOn(Schedulers.computation())
   }
}