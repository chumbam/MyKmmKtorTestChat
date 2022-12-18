package com.isaev.mykmmtestproject.data.remote

import com.isaev.mykmmtestproject.common.Resource
import com.isaev.mykmmtestproject.domain.Message
import kotlinx.coroutines.flow.Flow

interface MessageWebSocketService {

    companion object {
        const val URL_BASE = "ws://10.0.3.2:8082"
    }

    sealed class Endpoints(val url: String) {
        object ChatWebSocketConnection : Endpoints("$URL_BASE/my-chat-socket")
    }

    suspend fun initWebSocketSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    suspend fun observeMessage(): <Message>

    suspend fun closeWebSocketSession()

}