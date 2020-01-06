package br.com.bozelli.currencyconverter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import br.com.bozelli.currencyconverter.R
import br.com.bozelli.currencyconverter.constants.AUD
import br.com.bozelli.currencyconverter.constants.BRL
import br.com.bozelli.currencyconverter.constants.CAD
import br.com.bozelli.currencyconverter.constants.GBP
import br.com.bozelli.currencyconverter.constants.INR
import br.com.bozelli.currencyconverter.constants.USD
import com.dslplatform.json.StringCache

class CurrencyViewHolder(itemView: View): ViewHolder(itemView) {
    //Variables
    private val currencyTextView: TextView
    private val rateTextView: TextView
    private val flagImageView: ImageView

    //Constructor
    init {
        currencyTextView = itemView.findViewById(R.id.tv_currency)
        rateTextView = itemView.findViewById(R.id.tv_rate)
        flagImageView = itemView.findViewById(R.id.iv_flag)
    }

    //Method
    fun setupItem(currency: String, rate: Float) {
        currencyTextView.text = currency
        rateTextView.text = String.format("%.2f", rate)
        val resources = itemView.context.resources
        var flagDrawable: VectorDrawableCompat? = null
        if (currency == AUD) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_australia, null)
        }
        else if (currency == USD) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_usa, null)
        }
        else if (currency == CAD) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_canada, null)
        }
        else if (currency == GBP) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_uk, null)
        }
        else if (currency == BRL) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_brazil, null)
        }
        else if (currency == INR) {
            flagDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_flag_india, null)
        }
        flagImageView.setImageDrawable(flagDrawable)
    }
}