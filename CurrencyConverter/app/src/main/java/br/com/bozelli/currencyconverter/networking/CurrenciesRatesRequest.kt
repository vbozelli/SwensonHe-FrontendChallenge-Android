package br.com.bozelli.currencyconverter.networking

import br.com.bozelli.currencyconverter.interfaces.CurrenciesRatesRequestListener
import br.com.bozelli.currencyconverter.model.CurrenciesRatesResponse
import com.dslplatform.json.DslJson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.ref.WeakReference

class CurrenciesRatesRequest: Callback {
    //Variables
    private var listenerWeakReference: WeakReference<CurrenciesRatesRequestListener>

    //Constructor
    constructor(listener: CurrenciesRatesRequestListener) {
        listenerWeakReference = WeakReference(listener)
    }

    //Methods
    fun getCurrenciesRates() {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://data.fixer.io/api/latest?access_key=15f1bc0caaf7a90e428021e342d19d6a&symbols=AUD,USD,CAD,GBP,BRL,INR").get().build()
        client.newCall(request).enqueue(this)
    }

    //Callback
    override fun onFailure(call: Call, e: IOException) {
        val message = e.localizedMessage
        if (message != null) {
            val listener = listenerWeakReference.get()
            listener?.error(message)
        }
    }

    override fun onResponse(call: Call, response: Response) {
        val jsonBytes = response.body?.bytes()
        if (jsonBytes != null) {
            val dslJson = DslJson<Any>()
            try {
                val currenciesRatesResponse = dslJson.deserialize(CurrenciesRatesResponse::class.java, jsonBytes, jsonBytes.size)
                if (currenciesRatesResponse != null) {
                    val listener = listenerWeakReference.get()
                    listener?.sucess(currenciesRatesResponse)
                }
            }
            catch (e: IOException) {
                onFailure(call, e)
            }
        }
    }
}