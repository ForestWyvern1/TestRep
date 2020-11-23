package com.example.testapi.data

import com.example.testapi.UserProfile
import com.example.testapi.Users
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiSign {

    @GET("user/profile")
    fun getProfile(@Header("Token") accountToken: String?): Observable<UserProfile>

    @POST("users")
    fun registration(@Body user: Users): Observable<Users>

    @POST("user/login")
    fun autorisation(@Body user: Users): Observable<Users>

    companion object{
        fun createApi() : ApiSign{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val  retrofit = Retrofit.Builder()
                .baseUrl("http://wsk2019.mad.hakta.pro/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ApiSign::class.java)
        }
    }
}