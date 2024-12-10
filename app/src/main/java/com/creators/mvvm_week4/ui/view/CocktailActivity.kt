package com.creators.mvvm_week4.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.creators.mvvm_week4.R
import com.creators.mvvm_week4.databinding.ActivityMainBinding
import com.creators.mvvm_week4.ui.viewmodel.CocktailViewmodel
import com.creators.mvvm_week4.util.ResponseState
import com.creators.myapilearning.data.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint

//import com.creators.myapilearning.databinding.ActivityMainBinding


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
        binding.btnGetCocktail.setOnClickListener {
            viewmodel.fetchCocktail()
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
            ivUser.visibility = View.GONE
            tvText.text = "Loading. . ."
        }
    }

    private fun updateFailUI(error: String) {
        binding.apply {
            progressCircular.visibility = View.GONE
            ivUser.visibility = View.VISIBLE
            tvText.text = error
        }
    }

    private fun updateSuccessUI(response: CocktailModel) {
        binding.apply {
            progressCircular.visibility = View.GONE
            ivUser.visibility = View.VISIBLE
            Glide.with(this@CocktailActivity)
                .load(response.drinks?.get(0)?.strDrinkThumb)
                .placeholder(R.drawable.ic_launcher_foreground) //in case of loading or buffering
                .error(R.drawable.ic_launcher_background) //in case of failure
                .into(ivUser)

            tvText.text =
                "${response?.drinks?.get(0)?.idDrink?.first()}, \n${response?.drinks?.get(0)?.strAlcoholic}, \n${
                    response?.drinks?.get(
                        0
                    )?.strDrink
                }"
        }
    }
}