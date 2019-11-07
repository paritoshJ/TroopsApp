package com.social.troops.newWork.auth.video_view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import com.app.marketplace.sellBuyDevice.data.remote.RemoteRepo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import android.content.Context.MODE_PRIVATE
import android.content.Context
import android.util.Log
import com.social.troops.R
import com.social.troops.databinding.FragmentAuthmainBinding
import com.social.troops.databinding.FragmentLoginBinding
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.auth.AuthMainViewModel
import com.social.troops.newWork.auth.base.AuthBaseFragment
import com.social.troops.newWork.data.RepoListener
import com.social.troops.newWork.data.remote.ApiClient
import com.social.troops.newWork.domain.annotations.DataRequestType
import com.social.troops.newWork.domain.base.BaseResponse
import com.social.troops.newWork.domain.models.User


class AuthMainFragment : AuthBaseFragment<SplashVideoViewModel, FragmentAuthmainBinding>(), RepoListener {
    override fun getBindingVariable(): Int {
        return 0
    }




    override fun getViewModelClass(): Class<SplashVideoViewModel> {
        return SplashVideoViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_authmain
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun navigateToLogin() {
                getFragmentListener()?.navigateToLoginFragment()
            }

            override fun navigateToRegister() {
                getFragmentListener()?.navigateToRegisterFragment()
            }

        }

        viewBinding.loginBtn.setOnClickListener { viewModel.navigateToRegister() }
        viewBinding.registerBtn.setOnClickListener { viewModel.navigateToLogin() }





    }

    private fun showGetUserEmailDialog() {
       /* val dialog = GetUserEmailDialog(context!!, object : GetUserEmailDialog.GetUserEmailDialogListener {
            override fun onEmailSubmit(email: String) {
                this@LoginFragment.socialUserEmail = email
                viewModel.requestLogin(socialUserEmail!!, socialUserName!!, "facebook", socialId!!)
            }

            override fun showToast(message: String) {
                this@LoginFragment.showToast(message)
            }
        })
        dialog.show()*/
    }

    override fun showInputError(inputError: InputError) {
        when (inputError.errorType) {

        }
    }

  /*  fun loginWithGoogle() {
        activity?.let {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
            val mGoogleSignInClient = GoogleSignIn.getClient(it, gso)
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE)
        }
    }*/

   /* fun loginWithFacebook() {
        viewBinding.buttonLoginFacebookNative.performClick()
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN_GOOGLE) {
           /* try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    socialId = account.getId()
                    socialUserEmail = account.getEmail()
                    socialUserName = account.getDisplayName()
                    if (account.getPhotoUrl() != null)
                        socialUserPhoto = account.getPhotoUrl()!!.toString()
                    viewModel.requestLogin(socialUserEmail!!, socialUserName!!, "google", socialId!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
                e.message?.let { showToast(it) }
            }*/
        }
    }

    companion object {
        val TAG = "log^09"
        val RC_SIGN_IN_GOOGLE = 1008
        val ARG_SHOW_BACK = "show0back"
        fun newInstance() = AuthMainFragment()

        fun newInstance(showBack: Boolean): AuthMainFragment {
            val bundle = Bundle()
            bundle.putBoolean(ARG_SHOW_BACK, showBack)
            val frag = AuthMainFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    interface ViewActions {
        fun navigateToRegister()
        fun navigateToLogin()
    }


}



