package com.rosdyana.tomtommart.listener

import com.rosdyana.tomtommart.model.ProductEntity

interface OnClickItem {
    fun onClick(productEntity: ProductEntity)
}

interface OnClickItemAndAdd {
    fun onClick(productEntity: ProductEntity)
    fun onClickAdd(productEntity: ProductEntity)
}