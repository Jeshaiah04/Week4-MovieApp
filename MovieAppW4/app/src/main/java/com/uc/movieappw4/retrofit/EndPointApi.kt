package com.uc.movieappw4.retrofit

import com.uc.movieappw4.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointApi {
    @GET("movie/now_playing")
    suspend fun  getNowPlaying(
        @Query("api_key") apiKey:String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ):Response<NowPlaying>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id:Int,
        @Query("api_key") apiKey: String
    ): Response<Movie_Details>
}