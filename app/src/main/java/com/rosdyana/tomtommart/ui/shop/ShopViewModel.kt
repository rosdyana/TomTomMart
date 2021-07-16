package com.rosdyana.tomtommart.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.ProductEntity
import io.reactivex.disposables.CompositeDisposable

class ShopViewModel(val repository: Repository) : ViewModel() {
    private val _foods = MutableLiveData<ArrayList<ProductEntity>>()
    val foods : LiveData<ArrayList<ProductEntity>> = _foods

    private val _beverages = MutableLiveData<ArrayList<ProductEntity>>()
    val beverages : LiveData<ArrayList<ProductEntity>> = _beverages

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showFoodsData() {
        val foodsDisposable = repository.getFoods()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _foods.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage)}
            )
        compositeDisposable.add(foodsDisposable)
    }

    fun showBeveragesData() {
        val beveragesDisposable = repository.getBeverages()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _beverages.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage)}
            )
        compositeDisposable.add(beveragesDisposable)
    }

    fun addToCart(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }
}