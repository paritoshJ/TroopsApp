package com.app.marketplace.sellBuyDevice.domain.repos

import com.app.marketplace.sellBuyDevice.data.remote.RemoteRepo
import com.social.troops.newWork.data.RepoListener
import com.social.troops.newWork.data.remote.ApiClient
import com.social.troops.newWork.domain.annotations.DataRequestType
import com.social.troops.newWork.domain.base.BaseResponse
import kotlinx.coroutines.Deferred

class MyAccountRepo(val repoListener: RepoListener) {


    suspend fun requestSubmitContactUsData(name: String,
                                           email: String,
                                           phone: String,
                                           message: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.authorizedApiService.requestSubmitContactUsData(name, email, phone, message)
            override val dataRequestType: Int
                get() = DataRequestType.CONTACT_US
            override val repoListener: RepoListener
                get() = this@MyAccountRepo.repoListener
        }.executeApiRequest()
    }
}