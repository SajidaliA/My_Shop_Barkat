package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentUserNearByBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.HomeActivity
import com.peacetechsolution.myshopbarkat.util.Constant


class UserNearByFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var mBinding: FragmentUserNearByBinding
    private lateinit var mMap: GoogleMap
    private val userLocation = Location("Home")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentUserNearByBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        setClickListeners()
    }

    private fun setClickListeners() {
        mBinding.tvGoToHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            activity?.finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latitude = mPreferenceProvider?.getValue(Constant.LAT_KEY, "23.0225")?.toDouble()
        val longitude = mPreferenceProvider?.getValue(Constant.LONG_KEY, "72.5714")?.toDouble()
        latitude?.let { userLocation.latitude = it }
        longitude?.let { userLocation.longitude = it }
        val userHome = latitude?.let { longitude?.let { it1 -> LatLng(it, it1) } }
        val cameraPosition = userHome?.let {
            CameraPosition.Builder()
                .target(it)
                .zoom(17f)
                .build()
        }
        userHome?.let {
            MarkerOptions().title(getString(R.string.your_location))
                .icon(context?.let { context ->
                    bitmapDescriptorFromVector(
                        context, R.drawable.ic_home
                    )
                })
                .position(it)
        }?.let { googleMap.addMarker(it) }
        cameraPosition?.let { CameraUpdateFactory.newCameraPosition(it) }
            ?.let { googleMap.moveCamera(it) }
        searchUserNearBy()
    }

    private fun searchUserNearBy() {
        val listOfLocation: ArrayList<Location> = ArrayList()
        var usersNearBy = 0

        val locationB = Location("Other")
        locationB.latitude = 23.3813
            locationB.longitude = 72.5515
        listOfLocation.add(locationB)

        val locationC = Location("Other")
        locationC.latitude = 23.3975
        locationC.longitude = 72.5754
        listOfLocation.add(locationC)

        val locationD = Location("Other")
        locationD.latitude = 23.3346
        locationD.longitude = 72.5589
        listOfLocation.add(locationD)

        val locationE = Location("Other")
        locationE.latitude = 23.4179
        locationE.longitude = 72.5826
        listOfLocation.add(locationE)

        val locationF = Location("Other")
        locationF.latitude = 23.4385
        locationF.longitude = 72.5486
        listOfLocation.add(locationF)

        for (i in listOfLocation){
            val distance: Float = userLocation.distanceTo(i)/1000
            Log.e("TAG","searchUserNearBy() --> $distance")
            if (distance<=10){
                usersNearBy++
            }
        }

        startCountAnimation(usersNearBy)
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun startCountAnimation(users: Int) {
        val animator = ValueAnimator.ofInt(0, users)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            mBinding.tvNumberOfUser.text = animation.animatedValue.toString()
        }
        animator.start()
    }
}