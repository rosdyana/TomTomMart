package com.rosdyana.tomtommart.di

import com.rosdyana.tomtommart.ui.product.ProductViewModel
import com.rosdyana.tomtommart.ui.shop.ShopViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get())}
    viewModel { ProductViewModel(get()) }
}