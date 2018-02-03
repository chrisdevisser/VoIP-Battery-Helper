package com.chrisdevisser.voipbatteryhelper

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.util.Log

private const val TAG = "Logic"

fun updateCellRadio(context: Context) {
    val connected = isWifiConnected(context)
    logAction(connected)
    setCellRadioEnabled(context, !connected)
}

private fun logAction(connected: Boolean) {
    val wifiState = if (connected) "connected" else "disconnected"
    val takenAction = if (connected) "disabling" else "enabling"
    Log.i(TAG, "WiFi $wifiState - $takenAction cell radio")
}

private fun isWifiConnected(context: Context): Boolean =
    (context.getSystemService(ConnectivityManager::class.java) ?: throw AndroidWasDumb("No ConnectivityManager"))
        .run {allNetworks.any {getNetworkInfo(it).isConnected}}

fun setCellRadioEnabled(context: Context, enabled: Boolean) {
    try {
        val telephonyService = context.getSystemService(TelephonyManager::class.java)
        val setMobileDataEnabledMethod = telephonyService.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.javaPrimitiveType)
        setMobileDataEnabledMethod?.invoke(telephonyService, enabled)
    } catch (e: Exception) {
        val action = if (enabled) "enable" else "disable"
        throw AndroidWasDumb("Failed to $action cell radio: $e")
    }
}
