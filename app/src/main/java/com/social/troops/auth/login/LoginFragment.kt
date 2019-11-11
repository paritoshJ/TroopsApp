package com.social.troops.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import android.content.Context.MODE_PRIVATE
import android.content.Context
import android.util.Log
import com.social.troops.R
import com.social.troops.databinding.FragmentLoginBinding
import com.social.troops.AppManager
import com.social.troops.auth.base.AuthBaseFragment
import com.social.troops.data.RepoListener
import com.social.troops.data.remote.ApiClient
import com.social.troops.data.remote.RemoteRepo
import com.social.troops.domain.annotations.DataRequestType
import com.social.troops.domain.base.BaseResponse
import com.social.troops.domain.models.User


class LoginFragment : AuthBaseFragment<LoginViewModel, FragmentLoginBinding>(), RepoListener {
    override fun getBindingVariable(): Int {
        return 0
    }

    private var socialUserName: String? = null
    private var socialUserEmail: String? = null
    private var socialId: String? = null
    private var socialUserPhoto: String? = null
    private var showBack = false;
    private var device_id=""



    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {

            override fun onRoleSetSuccessful(message: String) {
                showToast(message)
                getFragmentListener()?.navigateToHomeActivity()
            }

            override fun onBackClicked() {
                activity?.onBackPressed()
            }

            override fun onFacebookLoginClicked() {
               // loginWithFacebook()
            }

            override fun onGoogleLoginClicked() {
               // loginWithGoogle()
            }

            override fun navigateToRegister() {
                getFragmentListener()?.navigateToRegisterFragment()
            }

            override fun navigateToForgotPassword() {
                getFragmentListener()?.navigateToForgotPasswordFragment()
            }

            override fun onLoginSuccessful(message: String) {
               showRoleDialog()
            }

        }

        viewBinding.signInBtn.setOnClickListener { viewModel.navigateToRegister() }
        viewBinding.forgotPasswd.setOnClickListener { viewModel.navigateToForgotPassword() }

      /*  viewBinding.buttonLoginFacebookNative.setFragment(this)
        viewBinding.buttonLoginFacebookNative.loginBehavior = LoginBehavior.WEB_ONLY
        viewBinding.buttonLoginFacebookNative.setPermissions(Arrays.asList<String>("public_profile", "email"))
        viewBinding.buttonLoginFacebookNative.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        GraphRequest.GraphJSONObjectCallback { `object`, response ->
                            socialId = null
                            socialUserEmail = null
                            socialUserName = null
                            socialUserPhoto = null
                            //                                socialGender = null;
                            try {
                                if (`object`.has("id"))
                                    socialId = `object`.getString("id")
                                if (`object`.has("email"))
                                    socialUserEmail = `object`.getString("email")
                                if (`object`.has("name"))
                                    socialUserName = `object`.getString("name")
                                if (`object`.has("picture"))
                                    socialUserPhoto = `object`.getJSONObject("picture")
                                            .getJSONObject("data").getString("url")
                                //                                    if (object.has("gender"))
                                //                                        socialGender = object.getString("gender");
                                if (socialId == null) {
                                    showToast(getString(R.string.err_some_wrong))
                                    return@GraphJSONObjectCallback
                                }
                                //                                    socialUserPhoto = "http://graph.facebook.com/" + socialId + "/picture";
                                if (socialUserEmail == null) {
                                    showGetUserEmailDialog()
                                    return@GraphJSONObjectCallback
                                }
                                viewModel.requestLogin(socialUserEmail!!, socialUserName!!, "facebook", socialId!!)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        })
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                // App code
                showToast("onCancel");
            }

            override fun onError(exception: FacebookException) {
                // App code
                showToast("onError");
            }
        })

*/
//        viewBinding.buttonLogin.setOnClickListener {
//            viewModel.getUiScop().launch {
//                val res = AuthRepo(this@LoginFragment).requestUserLogin(viewModel.email, viewModel.password)
//                res?.apply {
//                    if (this.status) {
//                        AppManager.setUser(this.data)
//                        viewModel.viewActions.onLoginSuccessful(this.message)
//                    } else showToast(this.message)
//                }
//            }
//        }
        viewBinding.signInBtn.setOnClickListener {

            viewModel.email=viewBinding.editEmail.text.toString()
            viewModel.password=viewBinding.password.text.toString()


            if(!viewModel.onLoginClicked())
                viewModel.getUiScop().launch {
                val res = object : RemoteRepo<BaseResponse<User>> {

                    override val deferred: Deferred<BaseResponse<User>> get() = ApiClient.apiService.requestUserLogin(viewModel.email, viewModel.password,"android",device_id)
                    override val dataRequestType: Int get() = DataRequestType.USER_LOGIN
                    override val repoListener: RepoListener get() = this@LoginFragment

                }.executeApiRequest()

                res?.apply {
                    if (this.status) {
//                        AppManager.setUser(this.data)
                        AppManager.preferences?.edit()?.putString("auth_token",this.data.token)?.apply()
                        viewModel.userid=this.data.id.toString()
//                        viewModel.userid=this.data.id.toString()
                        viewModel.viewActions.onLoginSuccessful(this.message)
                    } else showToast(this.message)
                }
            }

        }

        /*if (showBack) {
            viewBinding.imageBack.visibility = View.VISIBLE
            viewBinding.constBack.isClickable = true
        }*/
        device_id= AppManager.preferences!!.getString("device_id","").toString()
        if(TextUtils.isEmpty(device_id)){
            activity?.let {
//                FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(it) { instanceIdResult ->
//                    val newToken = instanceIdResult.getToken()
//                    device_id=newToken;
//                    Log.e("newToken", newToken)
//                }
            }

        }



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
            InputErrorType.EMAIL -> {
                viewBinding.editEmail.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.PASSWORD -> {
                viewBinding.password.requestFocus()
                showToast(inputError.message)
            }
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
        fun newInstance() = LoginFragment()

        fun newInstance(showBack: Boolean): LoginFragment {
            val bundle = Bundle()
            bundle.putBoolean(ARG_SHOW_BACK, showBack)
            val frag = LoginFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_SHOW_BACK)) showBack = it.getBoolean(ARG_SHOW_BACK)
        }
    }


    private fun showRoleDialog(){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog

        // Initialize an array of colors
        val array = arrayOf("I AM A SELLER","I AM A BUYER")

        // Initialize a new instance of alert dialog builder object
        val builder = activity?.let { AlertDialog.Builder(it) }

        // Set a title for alert dialog
        builder?.setTitle("Please Select your Preference")

        // Set the single choice items for alert dialog with initial selection
        builder?.setSingleChoiceItems(array,-1,{_,which->
            // Get the dialog selected item
            val selection = array[which]

            // Try to parse user selected color string
            try {

                if(selection.equals(array[0])){
                    this!!.activity?.let { AppManager.getPreferences(it) }?.edit()
                        ?.putBoolean(AppManager.IS_USER_SELLER,true)?.apply()

                    this!!.activity?.let { AppManager.getPreferences(it) }?.edit()
                        ?.putBoolean(AppManager.IS_USER_BUYER,false)?.apply()

                }else{
                    this!!.activity?.let { AppManager.getPreferences(it) }?.edit()
                        ?.putBoolean(AppManager.IS_USER_BUYER,true)?.apply()

                    this!!.activity?.let { AppManager.getPreferences(it) }?.edit()
                        ?.putBoolean(AppManager.IS_USER_SELLER,false)?.apply()
                }

//                viewModel.userid= AppManager.getUser()?.id.toString()

                if(AppManager.preferences?.getBoolean(AppManager.IS_USER_BUYER,false)!!){
                    viewModel.roleid="2"
                }else{
                    viewModel.roleid="1"
                }

                viewModel.onRoleClicked()

            }catch (e:IllegalArgumentException){
                // Catch the color string parse exception
            }

            // Dismiss the dialog
            dialog.dismiss()
        })


        // Initialize the AlertDialog using builder object
        dialog = builder!!.create()

        // Finally, display the alert dialog
        dialog.show()
    }

    interface ViewActions {
        fun navigateToRegister()
        fun navigateToForgotPassword()
        fun onLoginSuccessful(message: String)
        fun onRoleSetSuccessful(message: String)
        fun onFacebookLoginClicked()
        fun onGoogleLoginClicked()
        fun onBackClicked()
    }


}



