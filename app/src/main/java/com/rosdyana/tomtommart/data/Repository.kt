package com.rosdyana.tomtommart.data

import com.rosdyana.tomtommart.model.DataBase
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.utils.ProductSavedType

class Repository(val dummyDataSource: DummyDataSource, val dataBase: DataBase) {
    fun getFoods() = dummyDataSource.getFoods()
    fun getBeverages() = dummyDataSource.getBeverages()

    fun addToCart(productEntity: ProductEntity, quantity: Int = 1) {
        val productList = dataBase.productDao().getProductById(
            productEntity.id,
            ProductSavedType.CART
        )

        if (productList == null) {
            val product = productEntity.copy(
                quantity = quantity,
                type = ProductSavedType.CART
            )
            dataBase.productDao().insertProduct(product)
        } else {
            val product = productList.copy(
                quantity = productList.quantity + quantity,
                type = ProductSavedType.CART
            )
            dataBase.productDao().deleteProduct(productEntity)
            dataBase.productDao().insertProduct(product)
        }
    }

    fun subtractCart(productEntity: ProductEntity, quantity: Int = 1) {
        if (productEntity.quantity > 1) {
            val product = productEntity.copy(
                quantity = productEntity.quantity - quantity
            )
            dataBase.productDao().deleteProduct(productEntity)
            dataBase.productDao().insertProduct(product)
        } else {
            dataBase.productDao().deleteProduct(productEntity)
        }
    }

    fun removeFromCart(id: Int) {
        dataBase.productDao().deleteProductById(id, ProductSavedType.CART)
    }

    fun getAllData(type: Int): List<ProductEntity> {
        return dataBase.productDao().getProducts(type)
    }

    fun getTotalPrice(type: Int): Double {
        return dataBase.productDao().getTotalPrice(type)
    }

    fun getTotalCartQty(): Int {
        return dataBase.productDao().getTotalCartItem()
    }
}