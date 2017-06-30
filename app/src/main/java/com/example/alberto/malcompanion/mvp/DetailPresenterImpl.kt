package com.example.alberto.malcompanion.mvp

import com.example.alberto.malcompanion.data.extensions.where
import com.example.alberto.malcompanion.data.repository.RetrofitMalDataStore
import com.example.alberto.malcompanion.model.AnimeItem
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import javax.inject.Inject

class DetailPresenterImpl @Inject constructor(val retrofitMalDataStore: RetrofitMalDataStore) : DetailPresenter {

   private lateinit var detailView: DetailView
   private lateinit var animeItem: AnimeItem

   companion object {
      enum class UpdateType {
         DECREMENT, INCREMENT
      }
   }

   override fun onCreate(view: DetailView) {
      this.detailView = view
   }

   override fun onResume() {
   }

   override fun setAnimeItem(animeItem: AnimeItem) {
      this.animeItem = animeItem
   }

   /**
    * Request anime info for synopsis and another elements when the view is loaded
    */
   override fun requestAnimeInfo() {
      retrofitMalDataStore.searchAnime(animeItem.title)
         .map { infoList -> infoList.where(animeItem.id) }
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe { animeInfo -> detailView.showDetailInfo(animeInfo) }
   }

   override fun loadAnimeUserDetail() {
      detailView.showAnimeUserDetail(animeItem)
   }

   override fun requestEpisodeCountUpdate(updateType: UpdateType) {
      when (updateType) {
         UpdateType.INCREMENT -> animeItem.watchedEpisodes++
         UpdateType.DECREMENT -> animeItem.watchedEpisodes--
      }

      retrofitMalDataStore.updateAnime(animeItem)
         .map { response -> Timber.d("Response was: $response") }
//            .filter { response -> } //TODO Filter successful response
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe { response -> detailView.updateEpisodeCount(animeItem.watchedEpisodes) }
   }
}