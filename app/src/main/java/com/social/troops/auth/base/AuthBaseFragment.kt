package com.social.troops.newWork.auth.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel

abstract class AuthBaseFragment<T : BaseViewModel, B : ViewDataBinding> : BaseFragment<T, B>() {

    private var fragmentListener: AuthBaseFragmentListener? = null

    fun getFragmentListener(): AuthBaseFragmentListener? {
        return fragmentListener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AuthBaseFragmentListener)
            fragmentListener = context
        else
            throw RuntimeException("$context must implement AuthBaseFragmentListener")
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }
}