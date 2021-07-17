package com.rosdyana.tomtommart.model

import androidx.room.*

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getCarts(): List<CartEntity>

    @Query("SELECT * FROM cart WHERE productId = :productId")
    fun getCartByProductId(productId: Int): CartEntity? = null

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(cartEntity: CartEntity)

    @Query("UPDATE cart SET quantity = :quantity WHERE productId = :productId")
    fun updateCartByProductId(productId: Int, quantity: Int)

    @Delete
    fun deleteCart(cartEntity: CartEntity)

    @Query("DELETE FROM cart WHERE productId = :productId")
    fun deleteCartByProductId(productId: Int)
}