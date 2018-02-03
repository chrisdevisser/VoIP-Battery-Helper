package com.chrisdevisser.voipbatteryhelper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log

class WifiChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != WifiManager.NETWORK_STATE_CHANGED_ACTION) return

        try {
            updateCellRadio(context)
        } catch (e: AndroidWasDumb) {
            Log.e(TAG, e.toString())
        }
    }

    companion object {
        private val TAG = "WifiChangedReceiver"
    }
}
