package com.creators.mvvm_week4.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creators.mvvm_week4.R
import com.creators.mvvm_week4.databinding.CocktailCardBinding
import com.creators.myapilearning.data.model.Drinkmodel

class CocktailAdapater(
    val cocktailList: List<Drinkmodel?>?
) : RecyclerView.Adapter<CocktailAdapater.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CocktailCardBinding.bind(view)

        fun fillInformation(drink: Drinkmodel?) {
            binding.apply {
                tvTitle.text = drink?.idDrink.toString()
                tvDescription.text = drink?.strDrink.toString()
                Glide.with(itemView)
                    .load(drink?.strDrinkThumb)
                    .placeholder(R.drawable.ic_launcher_foreground) //in case of loading or buffering
                    .error(R.drawable.ic_launcher_background) //in case of failure
                    .into(igCard)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cocktail_card, parent, false)
        )
    }

    override fun getItemCount() = cocktailList?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fillInformation(cocktailList?.get(position))
    }
}