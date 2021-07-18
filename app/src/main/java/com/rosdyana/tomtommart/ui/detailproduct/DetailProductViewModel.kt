package com.rosdyana.tomtommart.ui.detailproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.ProductEntity

class DetailProductViewModel(val repository: Repository): ViewModel() {
    private val _detailProduct = MutableLiveData<ArrayList<ProductEntity>>()
    val detailProduct: LiveData<ArrayList<ProductEntity>> = _detailProduct

    private val _errorMessage = MutableLiveData<String>()

    fun addToCart(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }
}