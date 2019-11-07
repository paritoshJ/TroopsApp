package com.social.troops.newWork.data

import com.social.troops.newWork.domain.annotations.DataRequestType


interface RepoListener {
    fun onDataRequestFailed(@DataRequestType dataRequestType: Int, statusCode: Int, message: String)
    fun onNetworkConnectionError(@DataRequestType dataRequestType: Int, message: String)
    fun setDataRequestProgressIndicator(@DataRequestType dataRequestType: Int, visible: Boolean)
    fun <T> onDataRequestSucceed(dataRequestType: Int?, await: T?) {

    }
}