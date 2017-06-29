package com.example.alberto.malcompanion.data.net

import com.example.alberto.malcompanion.data.bean.Anime
import com.example.alberto.malcompanion.data.bean.MyList
import io.reactivex.Observable
import io.reactivex.Single
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
      : Single<Anime>

   @GET("/malappinfo.php")
   fun requestMyList(@Query("status") status: String,
                     @Query("u") user: String)
      : Single<MyList>

   @FormUrlEncoded
   @POST("/api/animelist/update/{id}")
   fun updateAnimeEpisode(@Path("id") id: Int,
                          @Field("data") data: String)
      : Single<ResponseBody>
}
