package com.chrisdevisser.voipbatteryhelper

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "MODIFY_PHONE_STATE: ${checkCallingOrSelfPermission(android.Manifest.permission.MODIFY_PHONE_STATE)}")


        try {
            updateCellRadio(this)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    companion object {
        private val TAG = "VoIP Battery"
    }
}
