package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentSignUpBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.util.Constant
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation
import com.peacetechsolution.myshopbarkat.util.invisible
import com.peacetechsolution.myshopbarkat.util.show
import java.text.SimpleDateFormat
import java.util.*

class SignUpFragment : BaseFragment() {

    private lateinit var mBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnSignUp.setOnClickListener {
            doSignUp()
        }
        mBinding.ivBack.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
        mBinding.tvDob.setOnClickListener {
            datePickerDialog()
        }

    }

    private fun datePickerDialog() {
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            mBinding.tvDob.text = simpleDateFormat.format(cal.timeInMillis)
        }
        context?.let { DatePickerDialog(it, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show() }
    }

    private fun doSignUp() {
        when {
            mBinding.etName.text.toString().isEmpty() -> {
                mBinding.etName.error = getString(R.string.please_enter_name)
                mBinding.etName.requestFocus()
            }

            mBinding.etMobile.text.toString().isEmpty() -> {
                mBinding.etMobile.error = getString(R.string.please_enter_mobile_number)
                mBinding.etMobile.requestFocus()
            }
            mBinding.etMobile.text.toString().length < 10 -> {
                mBinding.etMobile.error = getString(R.string.please_enter_valid_mobile_number)
                mBinding.etMobile.requestFocus()
            }
            mBinding.etPassword.text.toString().isEmpty() -> {
                mBinding.etPassword.error = getString(R.string.please_enter_password)
                mBinding.etPassword.requestFocus()
            }
            mBinding.etPassword.text.toString().length < 6 -> {
                mBinding.etPassword.error = getString(R.string.password_must_be_at_least_6_characters)
                mBinding.etPassword.requestFocus()
            }

            else -> {
                mBinding.btnSignUp.invisible()
                mBinding.progressBarSignUp.show()
                firebaseSignUp(mBinding.etMobile.text.toString(), mBinding.etPassword.text.toString())
            }
        }
    }

    private fun firebaseSignUp(email: String, password: String) {
        activity?.let {
//            mAuth?.createUserWithEmailAndPassword(email, password)
//                ?.addOnCompleteListener(
//                    it
//                ) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Toast.makeText(
//                            context, "Sign Up Successful!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        mPreferenceProvider?.setValue(USER_NAME_KEY, binding.etName.text.toString())
//                        mPreferenceProvider?.setValue(USER_EMAIL_KEY, binding.etEmail.text.toString())
//                        mPreferenceProvider?.setValue(MOBILE_NUMBER_KEY, binding.etMobile.text.toString())
//                        mPreferenceProvider?.setValue(PASSWORD_KEY, binding.etPassword.text.toString())
                        activity?.addReplaceFragmentWithAnimation(
                            R.id.lending_container, LoginFragment(),
                            addFragment = true,
                            addToBackStack = true,
                            R.anim.slide_in, R.anim.slide_out
                        )
//                    } else {
//                        binding.btnSignUp.show()
//                        binding.progressBarSignUp.hide()
//                        Toast.makeText(
//                            context, task.exception?.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
        }
    }
}
