package com.rosdyana.tomtommart.data

import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.model.ProductEntity
import io.reactivex.Observable

class DataSource {
    fun getBeverages(): Observable<ArrayList<ProductEntity>> {
        val beer = ProductEntity(
            name = "Beer",
            price = 20.0,
            picture = R.drawable.iv_beer
        )
        val cola = ProductEntity(
            name = "Cola",
            price = 15.0,
            picture = R.drawable.iv_cola
        )
        val orangeJuice = ProductEntity(
            name = "Orange Juice",
            price = 13.0,
            picture = R.drawable.iv_orange_juice
        )

        val data = listOf(beer, cola, orangeJuice)
        return Observable.just(ArrayList(data))
    }

    fun getFoods(): Observable<ArrayList<ProductEntity>> {
        val potatoChips = ProductEntity(
            name = "Potato Chips",
            price = 10.0,
            picture = R.drawable.iv_potato_chips
        )
        val chocolate = ProductEntity(
            name = "Chocolate",
            price = 15.0,
            picture = R.drawable.iv_chocolate
        )
        val italianSauce = ProductEntity(
            name = "Italian Sauce",
            price = 50.0,
            picture = R.drawable.iv_italian_sauce
        )
        val cornChips = ProductEntity(
            name = "Corn Chips",
            price = 15.0,
            picture = R.drawable.iv_corn_chips
        )

        val data = listOf(potatoChips, chocolate, italianSauce, cornChips)
        return Observable.just(ArrayList(data))
    }
}