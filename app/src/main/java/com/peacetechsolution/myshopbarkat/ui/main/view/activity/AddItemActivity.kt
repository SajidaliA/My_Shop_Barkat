package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.get
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityAddItemBinding
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.BottomSheetChooseCameraOrGallery
import com.peacetechsolution.myshopbarkat.util.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AddItemActivity : AppCompatActivity(),
    BottomSheetChooseCameraOrGallery.OnChooseClickListener,
    PermissionHelper.PermissionCallback {

    private var selectedQty: Int = 1
    private var selectedCategory = 0
    private lateinit var mBinding: ActivityAddItemBinding
    private lateinit var title: String
    private lateinit var imageUri: Uri
    private lateinit var currentPhotoPath: String
    private lateinit var imageFile: File
    private var permissionHelper: PermissionHelper? = null
    private var photoAvailable = false

    private var courses = arrayOf(
        "Select Category",
        "Cover", "Earphone",
        "Charger", "Battery",
        "Category 5", "Category 6", "Category 7", "Category 8", "Category 9", "Category 10"
    )

    private var permissions = arrayOf(
        android.Manifest.permission.CAMERA
    )

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        setUriToImage(imageUri)
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

    private fun setUriToImage(imageFileUri: Uri) {
        mBinding.ivPhotoTaken.setImageURI(null)
        mBinding.ivPhotoTaken.setImageURI(imageFileUri)
        mBinding.ivPhotoTaken.show()
        mBinding.llAddImage.invisible()
        photoAvailable = true
        imageFile = File(currentPhotoPath)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.colorPrimary)
        title = intent.getStringExtra(Constant.SELECTION_TYPE).toString()
        setCategorySpinner()
        permissionHelper = PermissionHelper(this, permissions, Constant.PERMISSION_REQUEST_CODE)
        createPhotoPath()
        imageUri = createImageUri()!!
        mBinding.apply {
            ivBack.setOnClickListener {
                finish()
            }
            tvTitleMain.text = title
            llAddImage.setOnClickListener {
                openBottomSheetToChooseCameraOrGallery()
            }
            ivPhotoTaken.setOnClickListener {
                openBottomSheetToChooseCameraOrGallery()
            }

            ivPlus.setOnClickListener {
                selectedQty++
                etQty.setText(selectedQty.toString())
            }
            ivMinus.setOnClickListener {
                if (selectedQty != 0) {
                    selectedQty--
                    etQty.setText(selectedQty.toString())
                }
            }

            btnAddItem.setOnClickListener {
                checkVelidations()
            }
        }
    }

    private fun setCategorySpinner() {
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )

        adapter.setDropDownViewResource(
            R.layout
                .spinner_dd
        )

        mBinding.spinnerCategory.adapter = adapter
        mBinding.spinnerCategory.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun checkVelidations() {
        if (!photoAvailable) {
            Toast.makeText(this, getString(R.string.please_add_item_image), Toast.LENGTH_SHORT)
                .show()
        } else if (mBinding.etTitle.text.toString().isEmpty()) {
            mBinding.etTitle.error = getString(R.string.please_enter_title)
            mBinding.etTitle.requestFocus()
        } else if (mBinding.etCostPrice.text.toString().isEmpty()) {
            mBinding.etCostPrice.error = getString(R.string.please_add_cost_price)
            mBinding.etCostPrice.requestFocus()
        } else if (mBinding.etSellPrice.text.toString().isEmpty()) {
            mBinding.etSellPrice.error = getString(R.string.please_add_sell_price)
            mBinding.etSellPrice.requestFocus()
        } else if(selectedCategory == 0){
            Toast.makeText(this, getString(R.string.please_select_one_category), Toast.LENGTH_LONG).show()
        } else{
            checkQty()
        }
    }

    private fun checkQty() {

        if (mBinding.etQty.text.toString() == "0") {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.qty_alert))
                .setMessage(getString(R.string.if_you_set_qty_0))
                .setPositiveButton(
                    R.string.proceed_with_0_qty
                ) { dialog, _ ->
                    uploadImageForPath()
                    dialog?.dismiss()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        } else {
            uploadImageForPath()
        }
    }

    private fun uploadImageForPath() {
        var imageServerPath = ""
        mBinding.btnAddItem.invisible()
        mBinding.progressBarAddItem.show()
        Toast.makeText(this, getString(R.string.product_added_successfully), Toast.LENGTH_SHORT)
            .show()
        finish()
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

    private fun showBottomSheetToGetCameraPermission() {

    }

    private fun hasPermissions() =
        permissionHelper?.checkSelfPermission(permissions)

    private fun requestPermission() {
        permissionHelper?.request(this)
    }

    private fun createImageUri(): Uri? {
        return FileProvider.getUriForFile(
            applicationContext,
            Constant.AUTHORITY,
            File(currentPhotoPath)
        )
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

    override fun onPermissionGranted() {
        contract.launch(imageUri)
    }

    override fun onPermissionDenied(permissionDeniedError: String) {

    }

    override fun onPermissionDeniedBySystem(permissionDeniedBySystem: String) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}