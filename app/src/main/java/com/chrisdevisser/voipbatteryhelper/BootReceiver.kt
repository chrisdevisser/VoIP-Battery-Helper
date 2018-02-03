package com.chrisdevisser.voipbatteryhelper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return
        try {
            updateCellRadio(context)
        } catch (e: AndroidWasDumb) {
            Log.e(TAG, e.toString())
        }
    }

    companion object {
        private val TAG = "BootReceiver"
    }
}
