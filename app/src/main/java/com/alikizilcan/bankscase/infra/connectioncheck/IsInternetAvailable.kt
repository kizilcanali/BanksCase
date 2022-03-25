package com.alikizilcan.bankscase.infra.connectioncheck


import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

object IsInternetAvailable {

    fun execute(socketFactory: SocketFactory): Boolean {
        return try{
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d("PING", "PING success.")
            true
        }catch (e: IOException){
            //No Internet
            false
        }
    }
}