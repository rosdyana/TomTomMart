package com.rosdyana.tomtommart.ui.shop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.adapter.CategoryAdapter
import com.rosdyana.tomtommart.model.CategoryData
import com.rosdyana.tomtommart.ui.product.ProductActivity
import kotlinx.android.synthetic.main.fragment_shop.*
import org.koin.android.viewmodel.ext.android.viewModel


class ShopFragment : Fragment(R.layout.fragment_shop) {
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
            startActivity(Intent(activity, ProductActivity::class.java))
        }
    }
    private val viewModel: ShopViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListCategories()
        observeCategories()

        viewModel.showDataCategories()
    }

    private fun setListCategories() {
        rv_categories.setHasFixedSize(true)
        rv_categories.adapter = categoryAdapter
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner, {
            categoryAdapter.setDataAdapter(it)
        })
    }
}