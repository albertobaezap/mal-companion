package com.example.alberto.malcompanion.data.net

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * API methods.
 */
interface MyAnimeListApi {

   @GET("/api/anime/search.xml")
   fun searchAnime(@Query("q") name: String)
      : Call<Anime>

   @GET("/malappinfo.php")
   fun requestMyList(@Query("status") status: String,
                     @Query("u") user: String)
      : Call<MyList>

   @POST
   fun updateAnimeEpisode(@Url url: String, @Body requestBody: RequestBody): Call<ResponseBody>
}
