package com.social.troops.newWork.domain.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.social.troops.newWork.data.RepoListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val mainViewJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + mainViewJob)

    var repoListener: RepoListener = object : RepoListener{
        override fun onDataRequestFailed(dataRequestType: Int, statusCode: Int, message: String) {
        }

        override fun onNetworkConnectionError(dataRequestType: Int, message: String) {
        }

        override fun setDataRequestProgressIndicator(dataRequestType: Int, visible: Boolean) {
        }
    }

    val showToast = MutableLiveData<String>()
    val showInputError = MutableLiveData<BaseFragment.InputError>()
    val closeKeyBoard = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        mainViewJob.cancel()
    }
}
