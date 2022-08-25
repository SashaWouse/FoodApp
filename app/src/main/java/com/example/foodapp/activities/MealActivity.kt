package com.example.foodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.Meal
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.viewModel.HomeViewModel
import com.example.foodapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var youTubeLink:String

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMVVM:MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMVVM = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInViews()

        loadingCase()
        mealMVVM.getMealDetail(mealId)
        observerMealDetailsLiveData()

        onYouTubeImgClick()
    }

    // redirection to YouTube
    private fun onYouTubeImgClick() {
        binding.imgYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMVVM.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t

                // Category info
                binding.tvCategoryInfo.text = "Catrgory : ${meal!!.strCategory}"
                // Location info
                binding.tvLocInfo.text = "Location : ${meal.strArea}"
                // Instructions
                binding.tvIstrSteps.text = meal.strInstructions

                youTubeLink = meal.strYoutube.toString()
            }

        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsBar.title = mealName
        binding.collapsBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsBar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    // visibility
    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFavourites.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategoryInfo.visibility = View.INVISIBLE
        binding.tvLocInfo.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnFavourites.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.tvLocInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}