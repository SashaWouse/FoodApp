package com.example.foodapp.retrofit

import com.example.foodapp.data.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>
}