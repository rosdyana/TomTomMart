package com.rosdyana.tomtommart.data

import com.rosdyana.tomtommart.model.DataBase
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.utils.ProductSavedType

class Repository(val dataSource: DataSource, val dataBase: DataBase) {
    fun getFoods() = dataSource.getFoods()
    fun getBeverages() = dataSource.getBeverages()

    fun addToCart(productEntity: ProductEntity, quantity: Int = 1) {
        val productList = dataBase.productDao().getProductById(
            productEntity.id,
            ProductSavedType.CART
        )

        if (productList.isEmpty()) {
            val product = productEntity.copy(
                quantity = quantity,
                type = ProductSavedType.CART
            )
            dataBase.productDao().insertProduct(product)
        } else {
            val product = productList[0].copy(
                quantity = productList[0].quantity + quantity,
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
        return dataBase.productDao().getProducts(type).orEmpty()
    }
}