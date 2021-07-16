package com.rosdyana.tomtommart.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rosdyana.tomtommart.utils.ProductSavedType
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "picture")
    var picture: Int = 0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "price")
    var price: Double = 0.0,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,
    @ColumnInfo(name = "type")
    var type: Int = ProductSavedType.NEW

) : Parcelable {

    val priceToQty get() = quantity * price
}
