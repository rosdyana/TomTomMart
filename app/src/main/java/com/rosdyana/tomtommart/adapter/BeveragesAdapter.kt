package com.rosdyana.tomtommart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.listener.OnClickItemAndAdd
import com.rosdyana.tomtommart.model.ProductEntity
import kotlinx.android.synthetic.main.item_product.view.*
import java.text.DecimalFormat

class BeveragesAdapter(): RecyclerView.Adapter<BeveragesAdapter.BeveragesViewHolder>() {
    private var list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItemAndAdd? = null

    inner class BeveragesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productEntity: ProductEntity) {
            val decimalFormat = DecimalFormat("#.#")
            val price = decimalFormat.format(productEntity.price)
            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }

            itemView.btn_add_product.setOnClickListener {
                onClickListener?.onClickAdd(productEntity)
            }

            Glide.with(itemView).load(productEntity.picture).into(itemView.iv_picture_product)
            itemView.tv_name_product.text = productEntity.name
            itemView.tv_description_product.text = productEntity.description
            itemView.tv_price_product.text = "NTD $price"
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeveragesAdapter.BeveragesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return BeveragesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeveragesAdapter.BeveragesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataAdapter(data: List<ProductEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}