package com.example.foodapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

//    lateinit var api:TheMealDBApi
//
//    init {
//
//    }

    //Api initialization
    val api:TheMealDBApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()) // Json converter
            .build()
            .create(TheMealDBApi::class.java)
    }


}