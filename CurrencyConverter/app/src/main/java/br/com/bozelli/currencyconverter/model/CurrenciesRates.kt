package br.com.bozelli.currencyconverter.model

import android.os.Parcel
import android.os.Parcelable
import com.dslplatform.json.CompiledJson
import com.dslplatform.json.JsonAttribute

@CompiledJson
class CurrenciesRates(@JsonAttribute(name = "AUD") val aud: Float, @JsonAttribute(name = "USD") val usd: Float, @JsonAttribute(name = "CAD") val cad: Float, @JsonAttribute(name = "GBP") val gbp: Float, @JsonAttribute(name = "BRL") val brl: Float, @JsonAttribute(name = "INR") val inr: Float): Parcelable {
    //Parcelable
    constructor(parcel: Parcel): this(parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(aud)
        parcel.writeFloat(usd)
        parcel.writeFloat(cad)
        parcel.writeFloat(gbp)
        parcel.writeFloat(brl)
        parcel.writeFloat(inr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrenciesRates> {
        override fun createFromParcel(parcel: Parcel): CurrenciesRates {
            return CurrenciesRates(parcel)
        }

        override fun newArray(size: Int): Array<CurrenciesRates?> {
            return arrayOfNulls(size)
        }
    }
}