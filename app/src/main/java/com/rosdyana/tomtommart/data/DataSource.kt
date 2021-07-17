package com.rosdyana.tomtommart.data

import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.model.ProductEntity
import io.reactivex.Observable

class DataSource {
    fun getBeverages(): Observable<ArrayList<ProductEntity>> {
        val beer = ProductEntity(
            id = 1,
            name = "Beer",
            price = 20.0,
            picture = R.drawable.iv_beer
        )
        val cola = ProductEntity(
            id = 2,
            name = "Cola",
            price = 15.0,
            picture = R.drawable.iv_cola
        )
        val orangeJuice = ProductEntity(
            id = 3,
            name = "Orange Juice",
            price = 13.0,
            picture = R.drawable.iv_orange_juice
        )

        val data = listOf(beer, cola, orangeJuice)
        return Observable.just(ArrayList(data))
    }

    fun getFoods(): Observable<ArrayList<ProductEntity>> {
        val potatoChips = ProductEntity(
            id = 4,
            name = "Potato Chips",
            price = 10.0,
            picture = R.drawable.iv_potato_chips
        )
        val chocolate = ProductEntity(
            id = 5,
            name = "Chocolate",
            price = 15.0,
            picture = R.drawable.iv_chocolate
        )
        val italianSauce = ProductEntity(
            id = 6,
            name = "Italian Sauce",
            price = 50.0,
            picture = R.drawable.iv_italian_sauce
        )
        val cornChips = ProductEntity(
            id = 7,
            name = "Corn Chips",
            price = 15.0,
            picture = R.drawable.iv_corn_chips
        )

        val data = listOf(potatoChips, chocolate, italianSauce, cornChips)
        return Observable.just(ArrayList(data))
    }
}