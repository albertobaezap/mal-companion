package com.example.alberto.malcompanion.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.alberto.malcompanion.R
import com.example.alberto.malcompanion.R.string.anime_detail_progress
import com.example.alberto.malcompanion.dagger.AppComponent
import com.example.alberto.malcompanion.model.AnimeInfo
import com.example.alberto.malcompanion.model.AnimeItem
import com.example.alberto.malcompanion.mvp.DetailPresenter
import com.example.alberto.malcompanion.mvp.DetailPresenterImpl
import com.example.alberto.malcompanion.mvp.DetailView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * Detailed view for a selected anime item
 */
class DetailActivity : BaseActivity(), DetailView {

   @Inject
   protected lateinit var detailPresenter: DetailPresenter

   companion object {
      val EXTRA_ITEM = "anime_item"
      val EXTRA_TRANSITION_NAME = "anime_transition_name"
   }

   var totalEpisodes: Int = 0

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_detail)
      window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
      window.statusBarColor = Color.TRANSPARENT;

      //Get the serialized object as anime item
      val animeItem = intent.getSerializableExtra(EXTRA_ITEM) as AnimeItem
      totalEpisodes = animeItem.totalEpisodes

      detailPresenter.onCreate(this)
      detailPresenter.setAnimeItem(animeItem)

      finishEnterAnimation(animeItem.image)

      episode_decrement_button.setOnClickListener {
         detailPresenter.requestEpisodeCountUpdate(DetailPresenterImpl.Companion.UpdateType.DECREMENT)
         companion_view_detail.displayText(resources.getString(R.string.episode_decrement))
      }

      episode_increment_button.setOnClickListener {
         detailPresenter.requestEpisodeCountUpdate(DetailPresenterImpl.Companion.UpdateType.INCREMENT)
         companion_view_detail.displayText(resources.getString(R.string.episode_increment))
      }

      detailPresenter.requestAnimeInfo()

   }

   override fun showDetailInfo(animeInfo: AnimeInfo) {
      synopsis_text.text = Html.fromHtml(animeInfo.synopsis, Html.FROM_HTML_MODE_COMPACT)
      anime_detail_mal_score.text = resources.getString(R.string.anime_detail_score_text,
         String.format("%.2f", animeInfo.score))
      anime_detail_status.text = resources.getString(R.string.anime_detail_status_text, animeInfo.status)
   }

   override fun updateEpisodeCount(count: Int) {
      episode_count_textview.text = String.format(resources.getString(anime_detail_progress),
         count, totalEpisodes)
   }

   override fun showAnimeUserDetail(animeItem: AnimeItem) {
      anime_detail_title.text = animeItem.title
      episode_count_textview.text = String.format(resources.getString(anime_detail_progress),
         animeItem.watchedEpisodes, animeItem.totalEpisodes)

      anime_detail_score.visibility = View.VISIBLE
      anime_detail_score.text = animeItem.score.toString()

      companion_view_detail.displayText(resources.getString(R.string.detail_enter, animeItem.title))
   }

   private fun finishEnterAnimation(imageUrl: String) {
      supportPostponeEnterTransition();
      //Get the transition to display the anime image
      val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)
      animeImage.transitionName = transitionName
      Picasso.with(this).load(imageUrl).into(animeImage, object : Callback {
         override fun onSuccess() {
            supportStartPostponedEnterTransition()
//            animeImage.setTopCrop()
         }

         override fun onError() {
            supportStartPostponedEnterTransition()
         }

      })
   }

   /**
    * Load dynamic elements when the animation is completed
    */
   override fun onEnterAnimationComplete() {
      super.onEnterAnimationComplete()
      detailPresenter.loadAnimeUserDetail()
   }

   override fun onDestroy() {
      super.onDestroy()
      companion_view_detail.cancelAnimation()
      anime_detail_score.visibility = View.INVISIBLE
   }

   override fun injectDependencies(appComponent: AppComponent) {
      appComponent.inject(this)
   }
}
