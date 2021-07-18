package com.rosdyana.tomtommart.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.adapter.CartAdapter
import com.rosdyana.tomtommart.listener.OnClickItemAddRemove
import com.rosdyana.tomtommart.listener.OnTotalChange
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.ui.detailproduct.DetailProductActivity
import com.rosdyana.tomtommart.utils.Constant
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat


class CartFragment : Fragment(R.layout.fragment_cart) {
    private val viewModel: CartViewModel by viewModel()

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(object : OnTotalChange {
            override  fun onTotalChange(total: Double) {
                val decimalFormat = DecimalFormat("#.##")
                val price = decimalFormat.format(total)

                tv_total_price.text = "NTD $price"
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCart()
        setListCart()
        viewModel.loadCartData()
    }

    private fun setListCart() {
        rv_cart.setHasFixedSize(true)
        rv_cart.adapter = cartAdapter
        cartAdapter.onClickListener = object : OnClickItemAddRemove {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addQtyProduct(productEntity)
            }

            override fun onClickSubstract(productEntity: ProductEntity) {
                substractQtyProduct(productEntity)
            }

            override fun onClickRemove(productEntity: ProductEntity) {
                removeFromCart(productEntity)
            }
        }
    }

    private fun observeCart() {
        viewModel.cartProduct.observe(viewLifecycleOwner, {
            cartAdapter.setDataAdapter(it)
        })
    }

    private fun addQtyProduct(productEntity: ProductEntity) {
        viewModel.addProduct(productEntity)
        viewModel.loadCartData()
    }

    private fun substractQtyProduct(productEntity: ProductEntity) {
        viewModel.subtractProduct(productEntity)
        viewModel.loadCartData()
    }

    private fun removeFromCart(productEntity: ProductEntity) {
        viewModel.removeProduct(productEntity)
        Toast.makeText(activity, "${productEntity.name} removed from cart", Toast.LENGTH_SHORT).show()
        viewModel.loadCartData()
    }
}