package com.rosdyana.tomtommart.model

import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getProducts(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteProductById(id: Int)
}