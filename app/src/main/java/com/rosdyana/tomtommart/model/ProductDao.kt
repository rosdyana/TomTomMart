package com.rosdyana.tomtommart.model

import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE type = :type")
    fun getProducts(type: Int): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id AND type = :type")
    fun getProductById(id: Int, type: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Query("DELETE FROM product WHERE id = :id AND type = :type")
    fun deleteProductById(id: Int, type: Int)

    @Query("SELECT SUM(quantity * price) FROM product WHERE type = :type")
    fun getTotalPrice(type: Int): Double

    @Query("SELECT SUM(quantity) FROM product WHERE type = 1")
    fun getTotalCartItem(): Int
}