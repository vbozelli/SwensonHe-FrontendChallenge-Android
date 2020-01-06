package br.com.bozelli.currencyconverter.utils

import android.content.Context
import android.content.Intent
import br.com.bozelli.currencyconverter.activity.CURRENCY_BUNDLE_KEY
import br.com.bozelli.currencyconverter.activity.ConverterActivity
import br.com.bozelli.currencyconverter.activity.RATE_BUNDLE_KEY

fun goToConverterScreen(context: Context, currency: String, rate: Float) {
    val intent = Intent(context, ConverterActivity::class.java)
    intent.putExtra(CURRENCY_BUNDLE_KEY, currency)
    intent.putExtra(RATE_BUNDLE_KEY, rate)
    context.startActivity(intent)
}