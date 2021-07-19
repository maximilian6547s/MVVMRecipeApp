package com.maximcuker.mvvmrecipeapp.interactors.app

import android.util.Log
import com.maximcuker.mvvmrecipeapp.util.TAG
import java.io.IOException
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import javax.net.SocketFactory

object DoesNetworkHaveInternet {

    fun execute(socketFactory: SocketFactory): Boolean {

        return try {
            Log.d(TAG, "PINGING GOOGLE")
            val socket = socketFactory.createSocket()
            socket.connect(InetSocketAddress("8.8.8.8", 53),1500)
            socket.close()
            Log.d(TAG, "PING SUCCESS")
            true
        } catch (e: IOException) {
            Log.e(TAG, "execute: No internet connection ${e}")
            false
        }
    }
}