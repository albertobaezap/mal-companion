package com.example.alberto.malcompanion.data.extensions

import com.example.alberto.malcompanion.data.bean.Entry

fun List<Entry>.where(id: Int): Entry {
   for (entry in this) {
      if (entry.id == id) return entry
   }
   return this.first()
}
