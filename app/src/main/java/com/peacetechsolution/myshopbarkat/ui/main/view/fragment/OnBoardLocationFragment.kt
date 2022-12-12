package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentMapLocationBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.util.Constant.IS_LOCATION_FETCHED
import com.peacetechsolution.myshopbarkat.util.Constant.LAT_KEY
import com.peacetechsolution.myshopbarkat.util.Constant.LONG_KEY
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation


@SuppressLint("MissingPermission")
class OnBoardLocationFragment : BaseFragment(), OnMapReadyCallback, BottomSheetNeedPermission.OnClickListener {

    private lateinit var mBinding: FragmentMapLocationBinding
    private lateinit var mMap: GoogleMap
    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val permissionReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when {
                it -> {
                    fetchLocation()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {

                    locationDenied(false)
                }
                else -> {
                    locationDenied(true)
                }
            }
        }
    private fun locationDenied(alwaysDeny: Boolean) {
        var denyType: BottomSheetNeedPermission.TYPE = BottomSheetNeedPermission.TYPE.AFTER_DENY_ONCE
        if (alwaysDeny) {
            denyType = BottomSheetNeedPermission.TYPE.AFTER_ALWAYS_DENY
        } else {
            //Normal deny
        }
        activity?.supportFragmentManager?.let { frm ->
            val locationNeedBottomSheet =
                BottomSheetNeedPermission.newInstance(denyType, this)
            locationNeedBottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetNeedPermission::class.java.simpleName)
            }
        }
    }

    private fun fetchLocation() {
        context?.let { context ->
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val manager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                   showGpsError()
                } else {
                    fusedLocationClient?.lastLocation
                        ?.addOnSuccessListener { location ->
                            saveLocation(location.latitude, location.longitude)
                        }
                        ?.addOnFailureListener {
                            Log.e("TAG","fetchLocation() --> Fail")
                        }
                }
            }
        }
    }

    private fun showGpsError() {
        activity?.supportFragmentManager?.let { frm ->
            val locationNeedBottomSheet =
                BottomSheetNeedPermission.newInstance(BottomSheetNeedPermission.TYPE.GPS, this)
            locationNeedBottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetNeedPermission::class.java.simpleName)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = activity?.let { LocationServices.getFusedLocationProviderClient(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMapLocationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        mBinding.btnSetHomeLocation.setOnClickListener {
            val latitude: Double = mMap.cameraPosition.target.latitude
            val longitude: Double = mMap.cameraPosition.target.longitude
            saveLocation(latitude, longitude)
        }

        mBinding.tvLocateMe.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun saveLocation(latitude: Double, longitude: Double) {
        mPreferenceProvider?.setValue(LAT_KEY, latitude)
        mPreferenceProvider?.setValue(LONG_KEY, longitude)
        mPreferenceProvider?.setValue(IS_LOCATION_FETCHED, true)
        activity?.addReplaceFragmentWithAnimation(
            R.id.lending_container, UserNearByFragment(),
            addFragment = true,
            addToBackStack = true,
            R.anim.slide_in, R.anim.slide_out
        )
    }

    private fun checkLocationPermission() {
        when {
            activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
            -> {
                openPermissionBS()
            }
            else -> {
                fetchLocation()
            }
        }
    }

    private fun openPermissionBS() {
        activity?.supportFragmentManager?.let { frm ->
            val locationNeedBottomSheet =
                BottomSheetNeedPermission.newInstance(BottomSheetNeedPermission.TYPE.PERMISSION, this)
            locationNeedBottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetNeedPermission::class.java.simpleName)
            }
        }
    }

    private fun requestLocationPermission() {
        permissionReqLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latitude = mPreferenceProvider?.getValue(LAT_KEY, "23.0225")?.toDouble()
        val longitude = mPreferenceProvider?.getValue(LONG_KEY, "72.5714")?.toDouble()
        moveCameraToLocation(latitude, longitude)
    }

    private fun moveCameraToLocation(latitude: Double?, longitude: Double?) {
        val targetLocation = latitude?.let { longitude?.let { it1 -> LatLng(it, it1) } }
        val cameraPosition = targetLocation?.let {
            CameraPosition.Builder()
                .target(it)
                .zoom(17f)
                .build()
        }
        cameraPosition?.let { CameraUpdateFactory.newCameraPosition(it) }
            ?.let { mMap.moveCamera(it) }
    }

    override fun onClick(selectedType: BottomSheetNeedPermission.TYPE) {
        when (selectedType) {
            BottomSheetNeedPermission.TYPE.PERMISSION -> {
                requestLocationPermission()
            }
            BottomSheetNeedPermission.TYPE.GPS -> {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            BottomSheetNeedPermission.TYPE.AFTER_DENY_ONCE -> {
                requestLocationPermission()
            }
            BottomSheetNeedPermission.TYPE.AFTER_ALWAYS_DENY -> {
                openAppPermissionSetting()
            }
        }
    }

    private fun openAppPermissionSetting() {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", context?.packageName, null)
            intent.data = uri
            startActivity(intent)

        } catch (e: Exception) {
            Toast.makeText(context, "Go to \"Settings\" and enable/ grant \"Location permission\" to continue", Toast.LENGTH_LONG).show();
        }
    }

    override fun onDismissBS() {

    }
}