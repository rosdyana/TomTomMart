package com.rosdyana.tomtommart.data

import com.rosdyana.tomtommart.model.CartEntity
import com.rosdyana.tomtommart.model.DataBase
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.utils.ProductSavedType

class Repository(val dataSource: DataSource, val dataBase: DataBase) {
    fun getFoods() = dataSource.getFoods()
    fun getBeverages() = dataSource.getBeverages()

    fun addToCart(cartEntity: CartEntity, quantity: Int = 1) {
        val currentCart = dataBase.cartDao().getCartByProductId(cartEntity.productId)

        if (currentCart == null) {
            val cart = CartEntity(
                productId = cartEntity.productId,
                price = cartEntity.price,
                quantity = quantity
            )
            dataBase.cartDao().insertCart(cart)
        } else {
            dataBase.cartDao().updateCartByProductId(
                productId = cartEntity.productId,
                quantity = quantity + currentCart.quantity
            )
        }
    }

    fun subtractCart(cartEntity: CartEntity) {
        if (cartEntity.quantity > 1) {
            dataBase.cartDao().updateCartByProductId(
                productId = cartEntity.productId,
                quantity = cartEntity.quantity - 1
            )
        } else {
            dataBase.cartDao().deleteCart(cartEntity)
        }
    }

    fun removeFromCart(id: Int) {
        dataBase.cartDao().deleteCartByProductId(id)
    }

    fun getAllData(): List<CartEntity> {
        return dataBase.cartDao().getCarts()
    }
}