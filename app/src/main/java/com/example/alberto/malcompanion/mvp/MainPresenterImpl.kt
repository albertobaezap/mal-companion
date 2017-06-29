package com.example.alberto.malcompanion.mvp

import com.example.alberto.malcompanion.BuildConfig
import com.example.alberto.malcompanion.data.repository.RetrofitMalDataStore
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Main presenter to handle interactor requests and send them to the view
 */
class MainPresenterImpl @Inject constructor(val retrofitMalDataStore: RetrofitMalDataStore) : MainPresenter {

   private lateinit var mainView: MainView

   override fun onCreate(view: MainView) {
      this.mainView = view
      requestMyAnimeList(BuildConfig.USERNAME, 1)
   }

   override fun onResume() {
      requestMyAnimeList(BuildConfig.USERNAME, 1)
   }

   //Request the anime list model
   override fun requestMyAnimeList(user: String, status: Int?) {
      retrofitMalDataStore.requestMyAnimeList("all", user) //TODO Change the status
         .map { animeList -> animeList.filter { item -> (if (status != null) item.status else status) == status } } //Filter by status
         .subscribe { animeList ->
            doAsync {
               uiThread {
                  mainView.displayList(animeList)
               }
            }
         }
   }



}