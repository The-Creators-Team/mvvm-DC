package com.creators.mvvm_week4.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creators.mvvm_week4.R
import com.creators.mvvm_week4.data.model.ItemMenu
import com.creators.mvvm_week4.databinding.ItemMenuCardBinding

class ItemMenuAdapater(
    val itemMenuList: List<ItemMenu?>?
) : RecyclerView.Adapter<ItemMenuAdapater.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMenuCardBinding.bind(view)

        fun fillInformation(itemMenu: ItemMenu?) {
            binding.apply {
                tvname.text = itemMenu?.nombre
                tvdescripcion.text = itemMenu?.descripcion
                tvprice.text =   itemMenu?.precio_costo.toString() + " $"
                Glide.with(itemView)
                    .load( "https://www.we-areunited.com/assets/united-commerce/images/items/" + itemMenu?.image_path)
                    .placeholder(R.drawable.ic_launcher_foreground) //in case of loading or buffering
                    .error(R.drawable.ic_launcher_background) //in case of failure
                    .into(ivImegen)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu_card, parent, false)
        )
    }

    override fun getItemCount() = itemMenuList?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fillInformation(itemMenuList?.get(position))
    }
}