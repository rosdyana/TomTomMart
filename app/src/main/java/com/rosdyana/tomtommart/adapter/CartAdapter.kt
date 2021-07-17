package com.rosdyana.tomtommart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.listener.OnClickItemAddRemove
import com.rosdyana.tomtommart.listener.OnTotalChange
import com.rosdyana.tomtommart.model.CartEntity
import kotlinx.android.synthetic.main.item_cart.view.*
import java.text.DecimalFormat

class CartAdapter(val listener : OnTotalChange): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var list: MutableList<CartEntity> = mutableListOf()
    var onClickListener: OnClickItemAddRemove? = null

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cartEntity: CartEntity){
            val decimalFormat = DecimalFormat("#.##")
            val price = decimalFormat.format(cartEntity.price)

            itemView.setOnClickListener {
                onClickListener?.onClick(cartEntity)
            }

            itemView.btn_delete_cart.setOnClickListener {
                onClickListener?.onClickRemove(cartEntity)
            }

            itemView.btn_min_cart.setOnClickListener {
                onClickListener?.onClickSubstract(cartEntity)
            }

            itemView.btn_plus_cart.setOnClickListener {
                onClickListener?.onClickAdd(cartEntity)
            }


            Glide.with(itemView)
                .load(cartEntity.picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(itemView.iv_picture_cart)
            itemView.tv_name_cart.text = cartEntity.productName
            itemView.tv_description_cart.text = cartEntity.description
            itemView.tv_price_cart.text = "NTD $price"
            itemView.tv_value_cart.text = cartEntity.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val view: View =
        LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataAdapter(data: List<CartEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
        val total = list.sumOf{it.priceToQty}
        listener.onTotalChange(total)
    }
}