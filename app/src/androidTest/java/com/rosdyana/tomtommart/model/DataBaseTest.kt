package com.rosdyana.tomtommart.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rosdyana.tomtommart.data.DummyDataSource
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.ui.cart.CartViewModel
import com.rosdyana.tomtommart.utils.ProductSavedType
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DataBaseTest : TestCase() {
    private lateinit var dataBase: DataBase
    private lateinit var productDao: ProductDao
    private lateinit var repository: Repository
    private lateinit var dummyDataSource: DummyDataSource

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(
            context, DataBase::class.java
        ).build()
        dummyDataSource = DummyDataSource()
        productDao = dataBase.productDao()
        repository = Repository(dummyDataSource, dataBase)

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    @Test
    fun addToCartTest() {
        val productA = ProductEntity(
            id = 1,
            name = "Cola",
            price = 15.0,
        )

        val productB = ProductEntity(
            id = 2,
            name = "Potato Chips",
            price = 10.0,
        )

        val productC = ProductEntity(
            id = 3,
            name = "Corn Chips",
            price = 15.0,
        )

        // Add 3 item to cart
        repository.addToCart(productA, 1)
        repository.addToCart(productB, 1)
        repository.addToCart(productC, 1)
        assertEquals(3, repository.getTotalCartQty())
        assertEquals(40.0, repository.getTotalPrice(ProductSavedType.CART))

        // Add 1 item to cart
        repository.addToCart(productA, 1)
        assertEquals(4, repository.getTotalCartQty())
        assertEquals(55.0, repository.getTotalPrice(ProductSavedType.CART))

        // Subtract 1 item from cart
        val productD = dataBase.productDao().getProductById(productA.id, ProductSavedType.CART)
        repository.subtractCart(productD, 2)
        assertEquals(2, repository.getTotalCartQty())
        assertEquals(25.0, repository.getTotalPrice(ProductSavedType.CART))
    }
}