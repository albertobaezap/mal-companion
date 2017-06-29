package com.example.alberto.malcompanion.data.extensions

import com.example.alberto.malcompanion.data.bean.Entry
import com.example.alberto.malcompanion.model.AnimeInfo

/**
 * Search an element on the list with the requested ID
 */
fun List<AnimeInfo>.where(id: Int): AnimeInfo {
   for (entry in this) {
      if (entry.id == id) return entry
   }
   return this.last()
}
