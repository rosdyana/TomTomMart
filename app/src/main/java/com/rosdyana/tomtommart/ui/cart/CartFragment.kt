package com.rosdyana.tomtommart.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.adapter.CartAdapter
import com.rosdyana.tomtommart.listener.OnClickItemAddRemove
import com.rosdyana.tomtommart.listener.OnTotalChange
import com.rosdyana.tomtommart.model.CartEntity
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
            override fun onClick(cartEntity: CartEntity) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, cartEntity)
                startActivity(intent)
            }

            override fun onClickAdd(cartEntity: CartEntity) {
                addQtyProduct(cartEntity)
            }

            override fun onClickSubstract(cartEntity: CartEntity) {
                substractQtyProduct(cartEntity)
            }

            override fun onClickRemove(cartEntity: CartEntity) {
                removeFromCart(cartEntity)
            }
        }
    }

    private fun observeCart() {
        viewModel.cartProduct.observe(viewLifecycleOwner, {
            cartAdapter.setDataAdapter(it)
        })
    }

    private fun addQtyProduct(cartEntity: CartEntity) {
        viewModel.addProduct(cartEntity)
        viewModel.loadCartData()
    }

    private fun substractQtyProduct(cartEntity: CartEntity) {
        viewModel.subtractProduct(cartEntity)
        viewModel.loadCartData()
    }

    private fun removeFromCart(cartEntity: CartEntity) {
        viewModel.removeProduct(cartEntity)
        Toast.makeText(activity, "Product removed from cart", Toast.LENGTH_SHORT).show()
        viewModel.loadCartData()
    }
}