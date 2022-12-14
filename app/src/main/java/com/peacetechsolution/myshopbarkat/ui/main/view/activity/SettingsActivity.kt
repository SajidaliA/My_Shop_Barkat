package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivitySettingsBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.BottomSheetShowHistory
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.CategoryListFragment
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation


class SettingsActivity : BaseActivity(), BottomSheetShowHistory.OnHistoryApplyClickListener {

    private lateinit var mBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mBinding.tvCategories.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.container_settings, CategoryListFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }

        mBinding.tvReminder.setOnClickListener {
            openEditTextDialog()
        }

        mBinding.tvHistory.setOnClickListener {
            openHistoryBS()
        }
    }

    private fun openHistoryBS() {
        supportFragmentManager.let { frm ->
            val bottomSheet =
                BottomSheetShowHistory.newInstance(this)
            bottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetShowHistory::class.java.simpleName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun openEditTextDialog() {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_with_edit_text, null)
        dialogBuilder.setView(dialogView)

        val tvTitle = dialogView.findViewById<AppCompatTextView>(R.id.tvAlertTitle)
        val editText = dialogView.findViewById<AppCompatEditText>(R.id.etDialogTitle)

        tvTitle.text = getString(R.string.reminder_for_stock)
        editText.hint = getString(R.string.enter_number)
        editText.inputType = InputType.TYPE_CLASS_NUMBER


        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            dialogBuilder.dismiss()
        }
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dialogBuilder.dismiss()
        }
        dialogBuilder.show()
    }

    override fun onHistoryApply(selectedHistory: String) {

    }

}