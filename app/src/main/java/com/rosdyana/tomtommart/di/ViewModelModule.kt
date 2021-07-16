package com.rosdyana.tomtommart.di

import com.rosdyana.tomtommart.ui.cart.CartViewModel
import com.rosdyana.tomtommart.ui.detailproduct.DetailProductViewModel
import com.rosdyana.tomtommart.ui.shop.ShopViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get())}
    viewModel { DetailProductViewModel(get()) }
    viewModel { CartViewModel(get()) }
}