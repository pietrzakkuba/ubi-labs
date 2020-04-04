package com.example.broadcastsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
//import android.support.v4.app.
import android.view.View
class SendBroadcastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_broadcast)
        configureReceive()
    }

    fun broadcastIntent(view: View) {
        val intent = Intent()
        intent.action = "com.example.sendbroadcast"
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)

    }

    var receiver: BroadcastReceiver? = null

    private fun configureReceive() {
        val filter = IntentFilter()
        filter.addAction("com.example.sendbroadcast")
        receiver = com.example.broadcastsample.BroadcastReceiver()
        registerReceiver(receiver, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
