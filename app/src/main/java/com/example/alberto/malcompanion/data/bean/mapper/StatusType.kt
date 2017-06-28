package com.example.alberto.malcompanion.data.bean.mapper

enum class StatusType(type: Int) {
   WATCHING(1), COMPLETED(2), ONHOLD(3), DROPPED(4), PLANTOWATCH(6);

   fun getStatusType(type: StatusType): String {
      return when (type) {
         WATCHING -> "watching"
         COMPLETED -> "completed"
         ONHOLD -> "on hold"
         DROPPED -> "dropped"
         PLANTOWATCH -> "plan to watch"
      }
   }
}
