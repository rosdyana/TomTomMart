package com.rosdyana.tomtommart.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.CartEntity
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.utils.ProductSavedType

class CartViewModel(val repository: Repository) : ViewModel() {
    private val _cartProduct = MutableLiveData<ArrayList<CartEntity>>()
    val cartProduct: LiveData<ArrayList<CartEntity>> = _cartProduct
    private var dataCart: ArrayList<CartEntity> = ArrayList()
    fun loadCartData() {
        dataCart.clear()
        dataCart.addAll(repository.getAllData())
        _cartProduct.postValue(dataCart)
    }

    fun addProduct(cartEntity: CartEntity) {
        repository.addToCart(cartEntity)
    }

    fun subtractProduct(cartEntity: CartEntity) {
        repository.subtractCart(cartEntity)
    }

    fun removeProduct(cartEntity: CartEntity) {
        repository.removeFromCart(cartEntity.productId)
    }
}