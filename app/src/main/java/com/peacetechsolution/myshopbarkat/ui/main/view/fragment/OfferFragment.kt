package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.data.model.Offer
import com.peacetechsolution.myshopbarkat.databinding.FragmentOfferBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.adapter.OfferAdapter
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.AddNewOfferActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.OfferDetailActivity


class OfferFragment : BaseFragment() {

    private lateinit var mBinding: FragmentOfferBinding
    private lateinit var offerAdapter: OfferAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentOfferBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val offers: ArrayList<Offer> =  ArrayList()
        offers.add(Offer("Test", "Offer title", " Offer description"))
        offers.add(Offer("Test", "Offer title", " Offer description"))
        offers.add(Offer("Test", "Offer title", " Offer description"))
        offers.add(Offer("Test", "Offer title", " Offer description"))
        offers.add(Offer("Test", "Offer title", " Offer description"))

        offerAdapter = OfferAdapter(
            offers,
            object : OfferAdapter.ClickListener {
                override fun itemClick(position: Int) {
                    startActivity(Intent(context, OfferDetailActivity::class.java))
                }
            }
        )
        mBinding.rvOffers.adapter = offerAdapter

        mBinding.fab.setOnClickListener {
            openAddNewOfferActivity()
        }
    }

    private fun openAddNewOfferActivity() {
        startActivity(Intent(context, AddNewOfferActivity::class.java))
    }

}