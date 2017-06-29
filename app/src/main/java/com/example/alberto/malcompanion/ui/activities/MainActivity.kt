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
import com.example.alberto.malcompanion.model.AnimeItem
import com.example.alberto.malcompanion.mvp.MainPresenter
import com.example.alberto.malcompanion.mvp.MainView
import com.example.alberto.malcompanion.ui.activities.DetailActivity.Companion.EXTRA_ITEM
import com.example.alberto.malcompanion.ui.activities.DetailActivity.Companion.EXTRA_TRANSITION_NAME
import com.example.alberto.malcompanion.ui.adapters.AnimeListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Main activity for the project
 */
class MainActivity : BaseActivity(), MainView {

   @Inject
   protected lateinit var mainPresenter: MainPresenter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      val toolbar = findViewById(R.id.toolbar) as Toolbar
      setSupportActionBar(toolbar)
      currentAnimeList.layoutManager = GridLayoutManager(this, 3)

      //Display welcome text
      companion_view_main.displayText(resources.getString(R.string.welcome_text))

      //Load list at start
      mainPresenter.onCreate(this)

   }

   override fun onResume() {
      super.onResume()

      //Reload list when resuming activity
      mainPresenter.onResume()
   }

   /**
    * Display the current user's MyAnimeList
    *
    * @param status Status to filter the list. If null, display the whole list
    */
   override fun displayList(animeList: List<AnimeItem>) {
      currentAnimeList.adapter = AnimeListAdapter(animeList, {
         animeItem: AnimeItem, imageView: ImageView ->
         companion_view_main.cancelAnimation() //Cancel animation before going into detail view

         //Show the detail view on item click
         showDetailView(animeItem, imageView)
      })
      companion_view_main.displayText(resources.getString(R.string.entries_found, animeList.size))
   }

   /**
    * Show the detail activity for the selected anime item with a shared element transition
    */
   fun showDetailView(animeItem: AnimeItem, animeView: ImageView) {
      val intent = Intent(MainActivity@ this, DetailActivity::class.java)
      intent.putExtra(EXTRA_ITEM, animeItem)
      intent.putExtra(EXTRA_TRANSITION_NAME, ViewCompat.getTransitionName(animeView))

      Timber.d("Transition name: ${ViewCompat.getTransitionName(animeView)}")

      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, animeView, ViewCompat.getTransitionName(animeView))
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
            mainPresenter.requestMyAnimeList(BuildConfig.USERNAME, 1)
         }
         R.id.display_all -> {
            mainPresenter.requestMyAnimeList(user = BuildConfig.USERNAME)
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
