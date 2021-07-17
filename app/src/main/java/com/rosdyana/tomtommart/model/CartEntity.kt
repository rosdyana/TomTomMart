package com.rosdyana.tomtommart.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "productId")
    var productId: Int = 0,
    @ColumnInfo(name = "productName")
    var productName: String = "",
    @ColumnInfo(name = "picture")
    var picture: Int = 0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "price")
    var price: Double = 0.0,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

) : Parcelable {

    val priceToQty get() = quantity * price
}
