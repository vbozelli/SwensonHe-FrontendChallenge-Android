package br.com.bozelli.currencyconverter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.bozelli.currencyconverter.R
import br.com.bozelli.currencyconverter.constants.AUD
import br.com.bozelli.currencyconverter.constants.BRL
import br.com.bozelli.currencyconverter.constants.CAD
import br.com.bozelli.currencyconverter.constants.GBP
import br.com.bozelli.currencyconverter.constants.INR
import br.com.bozelli.currencyconverter.constants.USD
import br.com.bozelli.currencyconverter.model.CurrenciesRates
import br.com.bozelli.currencyconverter.utils.goToConverterScreen
import br.com.bozelli.currencyconverter.viewholder.CurrencyViewHolder

class CurrenciesAdapter(private val currenciesRates: CurrenciesRates, private val context: Context): Adapter<CurrencyViewHolder>(), OnClickListener {
    //Constants
    private val currencies = arrayOf(AUD, USD, CAD, GBP, BRL, INR)

    //Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent,false))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(this)
        val currency = currencies[position]
        val rate: Float
        if (currency == AUD) {
            rate = currenciesRates.aud
        }
        else if (currency == USD) {
            rate = currenciesRates.usd
        }
        else if (currency == CAD) {
            rate = currenciesRates.cad
        }
        else if (currency == GBP) {
            rate = currenciesRates.gbp
        }
        else if (currency == BRL) {
            rate = currenciesRates.brl
        }
        else if (currency == INR) {
            rate = currenciesRates.inr
        }
        else {
            rate = 0f
        }
        holder.setupItem(currency, rate)
    }

    //OnClickListener
    override fun onClick(view: View) {
        val position = view.tag as Int
        val currency = currencies[position]
        val rate: Float
        if (currency == AUD) {
            rate = currenciesRates.aud
        }
        else if (currency == USD) {
            rate = currenciesRates.usd
        }
        else if (currency == CAD) {
            rate = currenciesRates.cad
        }
        else if (currency == GBP) {
            rate = currenciesRates.gbp
        }
        else if (currency == BRL) {
            rate = currenciesRates.brl
        }
        else if (currency == INR) {
            rate = currenciesRates.inr
        }
        else {
            rate = 1f
        }
        goToConverterScreen(context, currency, rate)
    }
}