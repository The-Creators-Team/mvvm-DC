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
import com.creators.mvvm_week4.data.model.ItemMenu
import com.creators.mvvm_week4.databinding.ActivityItemMenuBinding
import com.creators.mvvm_week4.ui.adapter.ItemMenuAdapater
import com.creators.mvvm_week4.ui.viewmodel.ItemMenuViewModel
import com.creators.mvvm_week4.util.ResponseState
import com.creators.mvvm_week4.util.ResponseStateItemMenu
import com.creators.myapilearning.data.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemMenuActivity : AppCompatActivity() {

    val viewmodel: ItemMenuViewModel by viewModels()
    lateinit var binding: ActivityItemMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.item_menu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewmodel.itemmenuModel.observe(this, { response ->
            when (response) {
                is ResponseStateItemMenu.Loading -> updateLoadingUI()
                is ResponseStateItemMenu.Success -> updateSuccessUI(response.result.data)
                is ResponseStateItemMenu.Fail -> updateFailUI(response.error)
            }
        })
        viewmodel.fetchItemMenu()
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

    private fun updateSuccessUI(response: List<ItemMenu>) {
        binding.apply {
            progressCircular.visibility = View.GONE
            binding.rvItemMenu.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemMenuAdapater(response)
            }
        }
    }
}