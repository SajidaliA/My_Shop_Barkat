package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.peacetechsolution.myshopbarkat.data.model.Product
import com.peacetechsolution.myshopbarkat.data.model.ProductResponse
import com.peacetechsolution.myshopbarkat.data.source.network.NetworkResult
import com.peacetechsolution.myshopbarkat.databinding.FragmentProductsBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.adapter.ProductAdapter
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.ProductDetailActivity
import com.peacetechsolution.myshopbarkat.ui.main.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
ProductFragment : BaseFragment() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var mBinding : FragmentProductsBinding
    private var photos: ArrayList<Product> = ArrayList()
    private val homeViewModel: HomeViewModel by viewModels()
    private var loadMore: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentProductsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoadMoreListener()
        setUpObserver()
        getUserData()
        mBinding.endlessList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        productAdapter = ProductAdapter(
            photos,
            object : ProductAdapter.ClickListener {
                override fun itemClick(position: Int) {
                    startActivity(Intent(context, ProductDetailActivity::class.java))
                }
            }
        )
        mBinding.endlessList.adapter = productAdapter
    }

    private fun setUpObserver() {
        homeViewModel.userLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it != null) {
                handleUserData(it)
            }
        }
    }

    private fun setLoadMoreListener() {
        mBinding.endlessList.setEndlessScrollCallback {
            // Now list view can be set so that it will block load until certain task finish
//            endless_list.blockLoading()
            loadMore = true
            homeViewModel.updatePage()
            homeViewModel.fetchUserData()
        }
    }

    private fun handleUserData(result: NetworkResult<ProductResponse>) {
        when (result) {
            is NetworkResult.Loading -> {
                // show a progress bar
                Log.e("TAG", "handleUserData() --> Loading  $result")

            }
            is NetworkResult.Success -> {
                // bind data to the view
                Log.e("TAG", "handleUserData() --> Success  $result")

                result.data?.let { productAdapter.setData(it.photos, loadMore) }
            }
            is NetworkResult.Error -> {
                // show error message
                Log.e("TAG", "handleUserData() --> Error ${result.message}")

            }
        }
    }

    private fun getUserData() {
        homeViewModel.fetchUserData()
    }

}