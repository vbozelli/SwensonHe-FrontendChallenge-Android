package br.com.bozelli.currencyconverter.interfaces

import br.com.bozelli.currencyconverter.model.CurrenciesRatesResponse

interface CurrenciesRatesRequestListener {
    fun error(message: String)
    fun sucess(response: CurrenciesRatesResponse)
}