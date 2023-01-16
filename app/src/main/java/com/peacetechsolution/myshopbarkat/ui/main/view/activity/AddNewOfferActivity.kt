package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityAddNewOfferBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.BottomSheetChooseCameraOrGallery
import com.peacetechsolution.myshopbarkat.util.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddNewOfferActivity : BaseActivity(),
    BottomSheetChooseCameraOrGallery.OnChooseClickListener,
    PermissionHelper.PermissionCallback {

    private lateinit var mBinding: ActivityAddNewOfferBinding
    private var photoAvailable = false
    private lateinit var imageFile: File
    private lateinit var imageUri: Uri
    private lateinit var currentPhotoPath: String
    private var permissionHelper: PermissionHelper? = null

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        setUriToImage(imageUri)
    }

    private var permissions = arrayOf(
        android.Manifest.permission.CAMERA
    )

    private fun setUriToImage(imageFileUri: Uri) {
        mBinding.ivPhotoTaken.setImageURI(null)
        mBinding.ivPhotoTaken.setImageURI(imageFileUri)
        mBinding.ivPhotoTaken.show()
        mBinding.llAddImage.invisible()
        photoAvailable = true
        imageFile = File(currentPhotoPath)
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val imageUri = data?.data
                if (null != imageUri) {
                    currentPhotoPath = imageUri.path.toString()
                    setUriToImage(imageUri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddNewOfferBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.landingStatusBar)
        permissionHelper = PermissionHelper(this, permissions, Constant.PERMISSION_REQUEST_CODE)
        createPhotoPath()
        imageUri = createImageUri()!!
        mBinding.llAddImage.setOnClickListener {
            openBottomSheetToChooseCameraOrGallery()
        }
    }

    private fun createPhotoPath(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun createImageUri(): Uri? {
        return FileProvider.getUriForFile(
            applicationContext,
            Constant.AUTHORITY,
            File(currentPhotoPath)
        )
    }

    private fun openBottomSheetToChooseCameraOrGallery() {
        supportFragmentManager.let { frm ->
            val bottomSheet =
                BottomSheetChooseCameraOrGallery.newInstance(this)
            bottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetChooseCameraOrGallery::class.java.simpleName)
            }
        }
    }

    override fun onClick(selectedType: BottomSheetChooseCameraOrGallery.TYPE) {
        when (selectedType) {
            BottomSheetChooseCameraOrGallery.TYPE.Camera -> {
                if (hasPermissions() == true) {
                    contract.launch(imageUri)
                } else {
                    showBottomSheetToGetCameraPermission()
                    requestPermission()
                }
            }
            BottomSheetChooseCameraOrGallery.TYPE.Gallery -> {
                val i = Intent()
                i.type = "image/*"
                i.action = Intent.ACTION_GET_CONTENT
                resultLauncher.launch(i)
            }
        }
    }

    private fun requestPermission() {
        permissionHelper?.request(this)
    }

    private fun showBottomSheetToGetCameraPermission() {

    }

    private fun hasPermissions() =
        permissionHelper?.checkSelfPermission(permissions)

    override fun onPermissionGranted() {
        contract.launch(imageUri)
    }

    override fun onPermissionDenied(permissionDeniedError: String) {

    }

    override fun onPermissionDeniedBySystem(permissionDeniedBySystem: String) {

    }
}