package br.com.bozelli.currencyconverter.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class CurrencyConverterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}