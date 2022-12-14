package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.data.model.ProductCategory
import com.peacetechsolution.myshopbarkat.databinding.FragmentCategoryListBinding
import com.peacetechsolution.myshopbarkat.ui.main.adapter.CategoryAdapter
import java.nio.file.Files.delete


class CategoryListFragment : Fragment() {

    private lateinit var mBinding: FragmentCategoryListBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private val categories: ArrayList<ProductCategory> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories.add(ProductCategory("Category 1"))
        categories.add(ProductCategory("Category 2"))
        categories.add(ProductCategory("Category 3"))
        categories.add(ProductCategory("Category 4"))
        categories.add(ProductCategory("Category 5"))
        categories.add(ProductCategory("Category 6"))
        categories.add(ProductCategory("Category 7"))
        categories.add(ProductCategory("Category 8"))
        categories.add(ProductCategory("Category 9"))
        categories.add(ProductCategory("Category 10"))
        categories.add(ProductCategory("Category 11"))
        categories.add(ProductCategory("Category 12"))

        categoryAdapter = CategoryAdapter(
            categories,
            object : CategoryAdapter.ClickListener {
                override fun itemClick(position: Int) {

                }

                override fun editClick(position: Int) {
                    openEditTextDialog(categories[position])
                }

                override fun deleteClick(position: Int) {
                    openTextViewDialog(
                        categories[position],
                        getString(R.string.delete_categoty),
                        getString(R.string.are_you_sure_you_want_to_delete_this_category),
                        getString(R.string.delete)
                    )
                }
            }
        )
        mBinding.rvCategories.adapter = categoryAdapter
        mBinding.btnAddNewCategory.setOnClickListener {
            openEditTextDialog(addCategory = true)
        }
    }

    private fun openEditTextDialog(
        productCategory: ProductCategory? = null,
        addCategory: Boolean = false
    ) {
        val dialogBuilder = AlertDialog.Builder(context).create()
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_with_edit_text, null)
        dialogBuilder.setView(dialogView)

        val tvTitle = dialogView.findViewById<AppCompatTextView>(R.id.tvAlertTitle)
        val editText = dialogView.findViewById<AppCompatEditText>(R.id.etDialogTitle)
        if (addCategory) {
            tvTitle.text = getString(R.string.add_new_category)
            editText.hint = getString(R.string.add_new_category)
        } else {
            tvTitle.text = getString(R.string.update_category)
            editText.setText(productCategory?.title)
        }

        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            dialogBuilder.dismiss()
            if (addCategory) {
                //add new category
            } else {
                //update category
            }
        }
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dialogBuilder.dismiss()
        }
        dialogBuilder.show()
    }

    private fun openTextViewDialog(
        productCategory: ProductCategory?,
        title: String,
        text: String,
        btnConfirmText: String
    ) {
        val dialogBuilder = AlertDialog.Builder(context).create()
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_with_text_view, null)
        dialogBuilder.setView(dialogView)

        val tvTitle = dialogView.findViewById<AppCompatTextView>(R.id.tvAlertTitle)
        val tvText = dialogView.findViewById<AppCompatTextView>(R.id.tvDialog)

        tvTitle.text = title
        tvText.text = text

        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.text = btnConfirmText
        btnSubmit.setOnClickListener {
            dialogBuilder.dismiss()

        }
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dialogBuilder.dismiss()
        }
        dialogBuilder.show()
    }
}