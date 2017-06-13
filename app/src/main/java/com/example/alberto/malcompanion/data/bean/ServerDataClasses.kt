package com.example.alberto.malcompanion.data.bean

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class Anime constructor(
   @field:ElementList(name = "entry", required = true, inline = true)
   @param:ElementList(name = "entry")
   val entryList: List<Entry>)

data class Entry constructor(
   @field:Element(name = "id")
   @param:Element(name = "id")
   val id: Int,

   @field:Element(name = "title")
   @param:Element(name = "title")
   val title: String,

   @field:Element(name = "english", required = false)
   @param:Element(name = "english")
   val english: String? = null,

   @field:Element(name = "synonyms", required = false)
   @param:Element(name = "synonyms")
   val synonyms: String? = null,

   @field:Element(name = "episodes")
   @param:Element(name = "episodes")
   val episodes: Int,

   @field:Element(name = "score")
   @param:Element(name = "score")
   val score: Float,

   @field:Element(name = "type")
   @param:Element(name = "type")
   val type: String,

   @field:Element(name = "status")
   @param:Element(name = "status")
   val status: String,

   @field:Element(name = "start_date")
   @param:Element(name = "start_date")
   val start_date: String,

   @field:Element(name = "end_date")
   @param:Element(name = "end_date")
   val end_date: String,

   @field:Element(name = "synopsis")
   @param:Element(name = "synopsis")
   val synopsis: String,

   @field:Element(name = "image")
   @param:Element(name = "image")
   val image: String)

@Root(name = "myanimelist")
data class MyList constructor(

   @field:Element(name = "myinfo")
   @param:Element(name = "myinfo")
   val myInfo: UserInfo,

   @field:ElementList(name = "anime", inline = true)
   @param:ElementList(name = "anime")
   val animeList: List<AnimeListEntry>)

data class UserInfo constructor(

   @field:Element(name = "user_id")
   @param:Element(name = "user_id")
   val user_id: Int,

   @field:Element(name = "user_name")
   @param:Element(name = "user_name")
   val user_name: String,

   @field:Element(name = "user_watching")
   @param:Element(name = "user_watching")
   val user_watching: Int,

   @field:Element(name = "user_completed")
   @param:Element(name = "user_completed")
   val user_completed: Int,

   @field:Element(name = "user_onhold")
   @param:Element(name = "user_onhold")
   val user_onhold: Int,

   @field:Element(name = "user_dropped")
   @param:Element(name = "user_dropped")
   val user_dropped: Int,

   @field:Element(name = "user_plantowatch")
   @param:Element(name = "user_plantowatch")
   val user_plantowatch: Int,

   @field:Element(name = "user_days_spent_watching")
   @param:Element(name = "user_days_spent_watching")
   val user_days_spent_watching: Float)

@Root(name = "anime")
data class AnimeListEntry constructor(

   @field:Element(name = "series_animedb_id")
   @param:Element(name = "series_animedb_id")
   val series_animedb_id: Int,

   @field:Element(name = "series_title")
   @param:Element(name = "series_title")
   val series_title: String,

   @field:Element(name = "series_synonyms", required = false)
   @param:Element(name = "series_synonyms")
   val series_synonyms: String? = null,

   @field:Element(name = "series_type")
   @param:Element(name = "series_type")
   val series_type: Int,

   @field:Element(name = "series_episodes", required = false)
   @param:Element(name = "series_episodes")
   val series_episodes: Int = 1,

   @field:Element(name = "series_status")
   @param:Element(name = "series_status")
   val series_status: Int,

   @field:Element(name = "series_start", required = false)
   @param:Element(name = "series_start")
   val series_start: String? = null,

   @field:Element(name = "series_end", required = false)
   @param:Element(name = "series_end")
   val series_end: String? = null,

   @field:Element(name = "series_image")
   @param:Element(name = "series_image")
   val series_image: String,

   @field:Element(name = "my_id", required = false)
   @param:Element(name = "my_id")
   val my_id: Int? = null,

   @field:Element(name = "my_watched_episodes", required = false)
   @param:Element(name = "my_watched_episodes")
   val my_watched_episodes: Int = 0,

   @field:Element(name = "my_start_date", required = false)
   @param:Element(name = "my_start_date")
   val my_start_date: String? = null,

   @field:Element(name = "my_finish_date", required = false)
   @param:Element(name = "my_finish_date")
   val my_finish_date: String? = null,

   @field:Element(name = "my_score", required = false)
   @param:Element(name = "my_score")
   val my_score: Int? = null,

   @field:Element(name = "my_status", required = false)
   @param:Element(name = "my_status")
   val my_status: Int? = null,

   @field:Element(name = "my_rewatching", required = false)
   @param:Element(name = "my_rewatching")
   val my_rewatching: Int? = null,

   @field:Element(name = "my_rewatching_ep", required = false)
   @param:Element(name = "my_rewatching_ep")
   val my_rewatching_ep: Int? = null,

   @field:Element(name = "my_last_updated", required = false)
   @param:Element(name = "my_last_updated")
   val my_last_updated: String? = null,

   @field:Element(name = "my_tags", required = false)
   @param:Element(name = "my_tags")
   val my_tags: String? = null)