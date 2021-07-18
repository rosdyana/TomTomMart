package com.rosdyana.tomtommart.ui.detailproduct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.ui.activity.MainActivity
import com.rosdyana.tomtommart.utils.Constant
import kotlinx.android.synthetic.main.activity_detail_product.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailProductActivity : AppCompatActivity() {
    private val viewModel: DetailProductViewModel by viewModel()

    private val product by lazy {
        intent.getParcelableExtra<ProductEntity>(Constant.DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        loadDetailData(product!!)
        addProductToCart(product!!)
    }

    private fun loadDetailData(productEntity: ProductEntity) {
        val decimalFormat = DecimalFormat("#.##")
        val price = decimalFormat.format(productEntity.price)

        Glide.with(this).load(productEntity.picture)
            .transition(DrawableTransitionOptions.withCrossFade()).into(iv_picture_detail)
        tv_name_detail.text = productEntity.name
        tv_description_detail.text = productEntity.description
        tv_price_detail.text = "NTD $price"
    }

    private fun addProductToCart(productEntity: ProductEntity) {
        btn_detail_add_to_cart.setOnClickListener {
            viewModel.addToCart(productEntity)
            Toast.makeText(this, "${productEntity.name} added to cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(Constant.CART, true)
            startActivity(intent)
            finish()
        }
    }
}