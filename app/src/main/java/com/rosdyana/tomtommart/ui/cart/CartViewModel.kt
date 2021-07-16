package com.rosdyana.tomtommart.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.utils.ProductSavedType

class CartViewModel(val repository: Repository) : ViewModel() {
    private val _cartProduct = MutableLiveData<ArrayList<ProductEntity>>()
    val cartProduct: LiveData<ArrayList<ProductEntity>> = _cartProduct
    private var dataCart: ArrayList<ProductEntity> = ArrayList()
    fun loadCartData() {
        dataCart.clear()
        dataCart.addAll(repository.getAllData(ProductSavedType.CART))
        _cartProduct.postValue(dataCart)
    }

    fun addProduct(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }

    fun subtractProduct(productEntity: ProductEntity) {
        repository.subtractCart(productEntity)
    }

    fun removeProduct(productEntity: ProductEntity) {
        repository.removeFromCart(productEntity.id)
    }
}