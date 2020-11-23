package com.example.testapi.data

import com.example.testapi.Case.apiKey
import com.example.testapi.Genres
import com.example.testapi.MovieModel
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("movie/popular?api_key=$apiKey&language=ru-RU")
    fun getPopular(): Observable<MovieModel>

    @GET("genre/movie/list?api_key=d5fc53ab2f320302df38cf43ef46a093&language=ru-RU")
    fun getGenre(): Observable<Genres>

    companion object{
        fun createApi() : Api{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}