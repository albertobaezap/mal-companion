package com.example.alberto.malcompanion.model

import java.io.Serializable

/**
 * Custom data class to show items in the RecyclerView
 */
class AnimeItem(
   val id: Int,
   val title: String,
   val image: String,
   val status: Int,
   val score: Int?,
   val _watchedEpisodes: Int,
   val totalEpisodes: Int) : Serializable {

   var watchedEpisodes: Int = _watchedEpisodes
      set(value) {
         if (value in 0..totalEpisodes) field = value
   }

   fun getProgress(): Int {
      if (totalEpisodes > 0) return watchedEpisodes * 100 / totalEpisodes
      else return 0
   }
}

/**
 * Extra data about the series to display on the detailed view
 */
data class AnimeInfo(
   val id: Int,
   val title: String,
   val synopsis: String,
   val score: Float,
   val status: String
)
