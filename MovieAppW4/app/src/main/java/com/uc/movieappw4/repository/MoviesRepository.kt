package com.uc.movieappw4.repository

import com.uc.movieappw4.retrofit.EndPointApi
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: EndPointApi){
    suspend fun getNowPlayingData(apiKey: String, language: String, page: Int)
            = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailsData(apiKey: String, id: Int)
            = api.getMovieDetails(id, apiKey)
}