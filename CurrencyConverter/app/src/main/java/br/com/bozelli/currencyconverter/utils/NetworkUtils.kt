package br.com.bozelli.currencyconverter.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun connectedToInternet(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork
        if (activeNetwork != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        }
        return false
    }
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    if (activeNetworkInfo != null) {
        val type = activeNetworkInfo.type
        return activeNetworkInfo.isConnected && (type == ConnectivityManager.TYPE_MOBILE || type == ConnectivityManager.TYPE_WIFI)
    }
    return false
}