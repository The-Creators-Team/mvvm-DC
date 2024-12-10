package com.creators.mvvm_week4.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.creators.mvvm_week4.R
import com.creators.mvvm_week4.databinding.ActivityMainBinding
import com.creators.mvvm_week4.ui.adapter.CocktailAdapater
import com.creators.mvvm_week4.ui.viewmodel.CocktailViewmodel
import com.creators.mvvm_week4.util.ResponseState
import com.creators.myapilearning.data.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CocktailActivity : AppCompatActivity() {

    val viewmodel: CocktailViewmodel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cocktail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewmodel.cocktailModel.observe(this, { response ->
            when (response) {
                is ResponseState.Loading -> updateLoadingUI()
                is ResponseState.Success -> updateSuccessUI(response.result)
                is ResponseState.Fail -> updateFailUI(response.error)
            }
        })
        viewmodel.fetchCocktail()
    }

    private fun updateLoadingUI() {
        binding.apply {
            progressCircular.visibility = View.VISIBLE
        }
    }

    private fun updateFailUI(error: String) {
        binding.apply {
            progressCircular.visibility = View.GONE

        }
    }

    private fun updateSuccessUI(response: CocktailModel) {
        binding.apply {
            progressCircular.visibility = View.GONE
            binding.rvCocktails.apply {
                val cocktailList = response.drinks
                layoutManager = LinearLayoutManager(context)
                adapter = CocktailAdapater(cocktailList)
            }
        }
    }
}