package com.uc.movieappw4.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.movieappw4.model.*
import com.uc.movieappw4.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {

    //get now playing data - movie detail - movie genre - language
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }
    val nowPlaying: LiveData<ArrayList<Result>>
        get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let { response ->
            if (response.isSuccessful) {
                _nowPlaying.postValue(response.body()?.results as ArrayList<Result>?)
            } else {
                Log.e("Retrieve Data", "Failed!")
            }
        }
    }

    val _movieDetail: MutableLiveData<Movie_Details> by lazy {
        MutableLiveData<Movie_Details>()
    }
    val movieDetail: LiveData<Movie_Details>
        get() = _movieDetail

    fun getMovieDetail(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailsData(apiKey, movieId).let { response ->
            if (response.isSuccessful) {
                _movieDetail.postValue(response.body() as Movie_Details)
            } else {
                Log.e("Get Movie Details Data", "Failed!")
            }
        }
    }

    val _movieGenre: MutableLiveData<List<Genre>> by lazy {
        MutableLiveData<List<Genre>>()
    }
    val moviegenre: LiveData<List<Genre>>
        get() = _movieGenre

    fun getMovieGenre(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailsData(apiKey, movieId).let { response ->
            if (response.isSuccessful) {
                _movieGenre.postValue(response.body() as List<Genre>)
            } else {
                Log.e("Get Movie Genre", "Failed!")
            }
        }
    }

    val _language: MutableLiveData<List<SpokenLanguage>> by lazy {
        MutableLiveData<List<SpokenLanguage>>()
    }
    val language: LiveData<List<SpokenLanguage>>
        get() = _language

    fun getLanguage(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailsData(apiKey, movieId).let { response ->
            if (response.isSuccessful) {
                _language.postValue(response.body() as List<SpokenLanguage>)
            } else {
                Log.e("Get Language", "Failed!")
            }
        }
    }
}