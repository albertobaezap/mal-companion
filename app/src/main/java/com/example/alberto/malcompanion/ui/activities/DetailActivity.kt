package com.example.alberto.malcompanion.ui.activities

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.alberto.malcompanion.R
import com.example.alberto.malcompanion.R.string.anime_detail_progress
import com.example.alberto.malcompanion.R.string.episode_increment
import com.example.alberto.malcompanion.model.AnimeItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

   companion object {
      val EXTRA_ITEM = "anime_item"
      val EXTRA_TRANSITION_NAME = "anime_transition_name"
   }

   var animeItem: AnimeItem? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_detail)
      supportPostponeEnterTransition();
      getWindow().getDecorView().systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
      getWindow().statusBarColor = Color.TRANSPARENT;

      animeItem = intent.getSerializableExtra(EXTRA_ITEM) as AnimeItem
      val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)
      animeImage.transitionName = transitionName
      Picasso.with(this).load(animeItem!!.image).into(animeImage, object : Callback {
         override fun onSuccess() {
            supportStartPostponedEnterTransition()
//            animeImage.setTopCrop()
         }

         override fun onError() {
            supportStartPostponedEnterTransition()
         }

      })

//      val palette = Palette.from((animeImage.drawable as BitmapDrawable).bitmap).generate()
//      val vibrant = palette.dominantSwatch
//      anime_detail_title.textColor = vibrant!!.titleTextColor

      episode_decrement_button.setOnClickListener {
         animeItem!!.watchedEpisodes--
         waifu_button_detail.displayText(resources.getString(R.string.episode_decrement))
         episode_count_textview.text = String.format(resources.getString(anime_detail_progress),
            animeItem!!.watchedEpisodes, animeItem!!.totalEpisodes)
      }

      episode_increment_button.setOnClickListener {
         animeItem!!.watchedEpisodes++
         waifu_button_detail.displayText(resources.getString(R.string.episode_increment))
         episode_count_textview.text = String.format(resources.getString(anime_detail_progress),
            animeItem!!.watchedEpisodes, animeItem!!.totalEpisodes)
      }

   }

   override fun onEnterAnimationComplete() {
      super.onEnterAnimationComplete()

      anime_detail_title.text = animeItem!!.title
      episode_count_textview.text = String.format(resources.getString(anime_detail_progress),
         animeItem!!.watchedEpisodes, animeItem!!.totalEpisodes)
      if (animeItem!!.score != null) {
         anime_detail_score.visibility = View.VISIBLE
         anime_detail_score.text = animeItem!!.score.toString()
      }
      waifu_button_detail.displayText(resources.getString(R.string.detail_enter, animeItem!!.title))

   }

   override fun onDestroy() {
      super.onDestroy()
      waifu_button_detail.cancelAnimation()
      anime_detail_score.visibility = View.INVISIBLE
   }
}
