package com.example.alberto.malcompanion.data.extensions

import com.example.alberto.malcompanion.data.bean.Entry

/**
 * Search an element on the list with the requested ID
 */
fun List<Entry>.where(id: Int): Entry {
   for (entry in this) {
      if (entry.id == id) return entry
   }
   return this.last()
}
