package br.com.bozelli.currencyconverter.model

import com.dslplatform.json.CompiledJson
import com.dslplatform.json.JsonAttribute

@CompiledJson
class CurrenciesRatesResponse(@JsonAttribute(name = "success") val sucess: Boolean, @JsonAttribute(name = "timestamp") val timestamp: Long, @JsonAttribute(name = "base") val base: String, @JsonAttribute(name = "date") val date: String, @JsonAttribute(name = "rates") val rates: CurrenciesRates)