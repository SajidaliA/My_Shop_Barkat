package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentSignUpBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.util.invisible
import com.peacetechsolution.myshopbarkat.util.show

class SignUpFragment : BaseFragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            doSignUp()
        }
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
    }

    private fun doSignUp() {
        when {
            binding.etName.text.toString().isEmpty() -> {
                binding.etName.error = getString(R.string.please_enter_name)
                binding.etName.requestFocus()
            }

            binding.etMobile.text.toString().isEmpty() -> {
                binding.etMobile.error = getString(R.string.please_enter_mobile_number)
                binding.etMobile.requestFocus()
            }
            binding.etMobile.text.toString().length < 10 -> {
                binding.etMobile.error = getString(R.string.please_enter_valid_mobile_number)
                binding.etMobile.requestFocus()
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = getString(R.string.please_enter_password)
                binding.etPassword.requestFocus()
            }
            binding.etPassword.text.toString().length < 6 -> {
                binding.etPassword.error = getString(R.string.password_must_be_at_least_6_characters)
                binding.etPassword.requestFocus()
            }

            else -> {
                binding.btnSignUp.invisible()
                binding.progressBarSignUp.show()
                firebaseSignUp(binding.etMobile.text.toString(), binding.etPassword.text.toString())
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
//                        activity?.addReplaceFragmentWithAnimation(
//                            R.id.lending_container, LoginFragment(),
//                            addFragment = true,
//                            addToBackStack = true,
//                            R.anim.slide_in, R.anim.slide_out
//                        )
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
