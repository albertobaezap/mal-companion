package com.example.alberto.malcompanion.data.bean.mapper

import com.example.alberto.malcompanion.data.bean.AnimeListEntry
import com.example.alberto.malcompanion.model.AnimeItem

/**
 * Mapper to convert anime request entries to a readable anime item
 */
class AnimeMapper {

   fun transform(animeList: List<AnimeListEntry>) = animeList.map { transform(it) }

   fun transform(anime: AnimeListEntry) = with(anime) {
      AnimeItem(id = series_animedb_id,
         title = series_title,
         image = series_image,
         score = my_score,
         status = my_status!!,
         _watchedEpisodes = my_watched_episodes,
         totalEpisodes = series_episodes)
   }
}