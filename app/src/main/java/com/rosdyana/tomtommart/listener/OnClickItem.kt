package com.rosdyana.tomtommart.listener

import com.rosdyana.tomtommart.model.CartEntity
import com.rosdyana.tomtommart.model.ProductEntity

interface OnClickItem {
    fun onClick(productEntity: ProductEntity)
}

interface OnClickItemAndAdd {
    fun onClick(productEntity: ProductEntity)
    fun onClickAdd(productEntity: ProductEntity)
}

interface OnClickItemAddRemove {
    fun onClick(productEntity: CartEntity)
    fun onClickAdd(productEntity: CartEntity)
    fun onClickSubstract(productEntity: CartEntity)
    fun onClickRemove(productEntity: CartEntity)
}