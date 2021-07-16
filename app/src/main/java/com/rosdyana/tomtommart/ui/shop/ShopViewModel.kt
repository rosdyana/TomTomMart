package com.rosdyana.tomtommart.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.CategoryData
import io.reactivex.disposables.CompositeDisposable

class ShopViewModel(val repository: Repository) : ViewModel() {
    private val _categories = MutableLiveData<ArrayList<CategoryData>>()
    val categories: LiveData<ArrayList<CategoryData>> = _categories

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showDataCategories() {
        val categoriesDisposable = repository.getCategories()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _categories.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) }
            )
        compositeDisposable.add(categoriesDisposable)
    }
}