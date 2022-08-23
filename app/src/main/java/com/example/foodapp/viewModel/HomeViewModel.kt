package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.*
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()

    fun getRandomMeal() {

        // retrofit call
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {

            // retrofit is connected to Api
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                // information of the random meal

                //checking for data
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0] //!! for non-null
                    Log.d("TEST", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")
                    randomMealLiveData.value = randomMeal
                }else {
                    return
                }
            }

            // connection is unsuccessfully
            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(call: Call<MealByCategoryList>, response: Response<MealByCategoryList>) {
                if(response.body()!=null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
                else {
                    return
                }
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())
            }
        })
    }

    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {

                // same as if (response.body()!=null){
                //                 categoriesLiveData.value = response.body()!!.categories
                //                }
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeViewModel",t.message.toString())
            }
        })
    }

    fun observeRandomMealLiveData():LiveData<Meal> {
        return randomMealLiveData
    }

    fun observerPopularItemsLiveData():LiveData<List<MealsByCategory>> {
        return popularItemsLiveData
    }

    fun observerCategoriesLiveData():LiveData<List<Category>> {
        return categoriesLiveData
    }

}