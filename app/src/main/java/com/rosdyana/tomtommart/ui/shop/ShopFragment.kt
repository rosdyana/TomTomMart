package com.rosdyana.tomtommart.ui.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.adapter.ProductAdapter
import com.rosdyana.tomtommart.listener.OnClickItemAndAdd
import com.rosdyana.tomtommart.model.ProductEntity
import com.rosdyana.tomtommart.ui.detailproduct.DetailProductActivity
import com.rosdyana.tomtommart.utils.Constant
import kotlinx.android.synthetic.main.fragment_shop.*
import org.koin.android.viewmodel.ext.android.viewModel


class ShopFragment : Fragment(R.layout.fragment_shop) {
    private val beverageAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }
    private val foodAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }
    private val viewModel: ShopViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListFoods()
        observeFoods()

        setListBeverages()
        observeBeverages()

        viewModel.showFoodsData()
        viewModel.showBeveragesData()
    }

    private fun observeBeverages() {
        viewModel.beverages.observe(viewLifecycleOwner, {
            beverageAdapter.setDataAdapter(it)
        })
    }

    private fun setListBeverages() {
        rv_beverages.setHasFixedSize(true)
        rv_beverages.adapter = beverageAdapter
        beverageAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toProductDetail(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity)
            }
        }
    }

    private fun observeFoods() {
        viewModel.foods.observe(viewLifecycleOwner, {
            foodAdapter.setDataAdapter(it)
        })
    }

    private fun setListFoods() {
        rv_foods.setHasFixedSize(true)
        rv_foods.adapter = foodAdapter
        foodAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toProductDetail(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity)
            }
        }
    }

    private fun addProductToCart(productEntity: ProductEntity) {
        viewModel.addToCart(productEntity)
        Toast.makeText(activity, "${productEntity.name} added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun toProductDetail(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
    }
}