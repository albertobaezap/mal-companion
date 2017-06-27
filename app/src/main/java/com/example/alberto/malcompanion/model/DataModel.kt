package com.example.alberto.malcompanion.model

import java.io.Serializable

/**
 * Custom data class to show items in the RecyclerView
 */
data class AnimeItem(
   val id: Int,
   val title: String,
   val image: String,
   val status: Int,
   val score: Int?,
   var watchedEpisodes: Int,
   val totalEpisodes: Int) : Serializable {

   fun getProgress(): Int {
      if (totalEpisodes > 0) return watchedEpisodes * 100 / totalEpisodes
      else return 0
   }
}

data class AnimeInfo(
   val id: Int,
   val title: String,
   val synopsis: String,
   val score: Float,
   val status: String
)
