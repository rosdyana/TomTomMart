package com.rosdyana.tomtommart.listener

import com.rosdyana.tomtommart.model.ProductEntity

interface OnClickItemAndAdd {
    fun onClick(productEntity: ProductEntity)
    fun onClickAdd(productEntity: ProductEntity)
}

interface OnClickItemAddRemove {
    fun onClick(productEntity: ProductEntity)
    fun onClickAdd(productEntity: ProductEntity)
    fun onClickSubstract(productEntity: ProductEntity)
    fun onClickRemove(productEntity: ProductEntity)
}