package com.rosdyana.tomtommart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.model.CategoryData
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val onClick: () -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var list: MutableList<CategoryData> = mutableListOf()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(categoryData: CategoryData) {
            itemView.setOnClickListener {
                if (adapterPosition == 0) {
                    onClick.invoke()
                }
            }

            Glide.with(itemView).load(categoryData.picture).into(itemView.iv_picture_category)
            itemView.tv_name_category.text = categoryData.name

            when (adapterPosition) {
                0 -> itemView.setBackground(
                    itemView.context.getDrawable(R.drawable.bg_rounded_seashell)
                )
                1 -> itemView.setBackground(
                    itemView.context.getDrawable(R.drawable.bg_rounded_honeydew)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataAdapter(data: List<CategoryData>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}