package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentLoginBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.HomeActivity
import com.peacetechsolution.myshopbarkat.util.*


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.btnLogin.setOnClickListener{
            //TODO:: remove this when login API done and un comment doLogin()
            mPreferenceProvider?.setValue(Constant.IS_USER_LOGIN, true)
            startActivity(Intent(context, HomeActivity::class.java))
            activity?.finish()

            //doLogin()
        }
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
    }

    private fun setupViewModel() {
        // Init ViewModel Here
    }

    private fun doLogin() {
        when {
            binding.etMobile.text.toString().isEmpty() -> {
                binding.etMobile.error = getString(R.string.please_enter_mobile_number)
                binding.etMobile.requestFocus()
            }
            !binding.etMobile.text.toString().isEmailValid() -> {
                binding.etMobile.error = getString(R.string.please_enter_valid_mobile_number)
                binding.etMobile.requestFocus()
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = getString(R.string.please_enter_password)
            }
            else -> {
                binding.btnLogin.invisible()
                binding.progressBar.show()
                firebaseLogin(binding.etMobile.text.toString(), binding.etPassword.text.toString())
            }
        }
    }

    private fun firebaseLogin(email: String, password: String) {
        activity?.let {
//            mAuth?.signInWithEmailAndPassword(email, password)
//                ?.addOnCompleteListener(
//                    it
//                ) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Toast.makeText(
//                            context, "Login Successful!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        mPreferenceProvider?.setValue(IS_USER_LOGIN, true)
//                        it.addReplaceFragmentWithAnimation(
//                            R.id.lending_container, GetLocationInfoFragment(),
//                            addFragment = true,
//                            addToBackStack = true,
//                            R.anim.slide_in, R.anim.slide_out
//                        )
//                    } else {
//                        binding.btnLogin.show()
//                        binding.progressBar.hide()
//                        Toast.makeText(
//                            context, task.exception?.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
        }
    }
}
