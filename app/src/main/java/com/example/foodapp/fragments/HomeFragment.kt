package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.data.Meal
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    //instance of viewBinding
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeMVVM:HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMVVM = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialization of binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMVVM.getRandomMeal()
        observerRandomMeal()

    }

    private fun observerRandomMeal() {
        homeMVVM.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.imgRandomMeal)
            }

        })
    }


}