package com.example.alberto.malcompanion.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.alberto.malcompanion.BuildConfig
import com.example.alberto.malcompanion.R
import com.example.alberto.malcompanion.dagger.AppComponent
import com.example.alberto.malcompanion.data.bean.mapper.AnimeMapper
import com.example.alberto.malcompanion.data.repository.AnimeListManager
import com.example.alberto.malcompanion.model.AnimeItem
import com.example.alberto.malcompanion.ui.activities.DetailActivity.Companion.EXTRA_ITEM
import com.example.alberto.malcompanion.ui.activities.DetailActivity.Companion.EXTRA_TRANSITION_NAME
import com.example.alberto.malcompanion.ui.adapters.AnimeListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import javax.inject.Inject

/**
 * Main activity for the project
 */
class MainActivity : BaseActivity() {

   @Inject
   protected lateinit var animeListManager: AnimeListManager

   var animeMapper: AnimeMapper = AnimeMapper()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      val toolbar = findViewById(R.id.toolbar) as Toolbar
      setSupportActionBar(toolbar)
      currentAnimeList.layoutManager = GridLayoutManager(this, 3)

      //Display welcome text
      waifu_button_main.displayText(resources.getString(R.string.welcome_text))

      //Load list at start
      displayList(1)

   }

   /**
    * Display the current user's MyAnimeList
    *
    * @param status Status to filter the list. If null, display the whole list
    */
   fun displayList(status: Int? = null) {
      doAsync {
         animeListManager.requestMyAnimeList(BuildConfig.USERNAME)
            .subscribe({ animeList ->
               uiThread {
                  //Retrieve the list with all the anime items and pass it to the adapter
                  val adapter = AnimeListAdapter(
                     //Transform from server data to model data
                     animeMapper.transform(animeList)
                        .filter { item -> (if (status != null) item.status else status) == status } //Filter by status
                        .sortedBy { it.title },
                     { animeItem: AnimeItem, imageView: ImageView ->
                        waifu_button_main.cancelAnimation() //Cancel animation before going into detail view

                        //Show the detail view on item click
                        showDetailActivity(animeItem, imageView)
                     })
                  currentAnimeList.adapter = adapter

                  waifu_button_main.displayText(resources.getString(R.string.entries_found, animeList.size))
               }
            }, {
               Timber.e("Error!")
            })
      }
   }

   /**
    * Show the detail activity for the selected anime item with a shared element transition
    */
   fun showDetailActivity(animeItem: AnimeItem, animeImage: ImageView) {
      val intent = Intent(MainActivity@ this, DetailActivity::class.java)
      intent.putExtra(EXTRA_ITEM, animeItem)
      intent.putExtra(EXTRA_TRANSITION_NAME, ViewCompat.getTransitionName(animeImage))

     Timber.d("Transition name: ${ViewCompat.getTransitionName(animeImage)}")

      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, animeImage, ViewCompat.getTransitionName(animeImage))
      startActivity(intent, options.toBundle())
   }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      // Inflate the menu; this adds items to the action bar if it is present.
      menuInflater.inflate(R.menu.menu_main, menu)
      return true
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      val id = item.itemId

      when (id) {
         R.id.display_watching -> {
            displayList(1)
         }
         R.id.display_all -> {
            displayList()
         }
         R.id.home -> {
            supportFinishAfterTransition()
            return true
         }
      }

      return super.onOptionsItemSelected(item)
   }

   override fun injectDependencies(appComponent: AppComponent) {
      appComponent.inject(this)
   }
}
