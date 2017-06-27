package com.example.alberto.malcompanion.data.bean.mapper

import com.example.alberto.malcompanion.data.bean.Entry
import com.example.alberto.malcompanion.model.AnimeInfo

class InfoMapper {

   fun transform(animeEntry: Entry) = with(animeEntry) {
      AnimeInfo(id = id,
         title = title,
         synopsis = synopsis,
         score = score,
         status = status)
   }
}

