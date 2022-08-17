package com.example.foodapp.retrofit

import com.example.foodapp.data.MealList
import retrofit2.Call
import retrofit2.http.GET

interface TheMealDBApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}