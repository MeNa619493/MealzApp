package com.example.mealzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mealzapp.databinding.ActivityMainBinding
import com.example.mealzapp.util.MealsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MealsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getMeals()
        val adapter = MealsAdapter()

        lifecycleScope.launch {
            viewModel.categories.collect {
                adapter.submitList(it?.categories)
                binding.categoryRv.adapter = adapter
            }
        }
    }
}