package br.com.bozelli.currencyconverter.activity

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.bozelli.currencyconverter.R
import br.com.bozelli.currencyconverter.views.ATMEditText
import java.util.*

//Constants
val CURRENCY_BUNDLE_KEY = "currency"
val RATE_BUNDLE_KEY = "rate"

class ConverterActivity: AppCompatActivity(), TextWatcher {
    //Variables
    private lateinit var baseCurrencyEditText: ATMEditText
    private lateinit var targetCurrencyTextView: TextView
    private lateinit var targetRateTextView: TextView
    private lateinit var currency: String
    private var rate: Float = 0f

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        if (savedInstanceState != null) {
            currency = savedInstanceState.getString(CURRENCY_BUNDLE_KEY)!!
            rate = savedInstanceState.getFloat(RATE_BUNDLE_KEY)
        }
        else {
            currency = intent.getStringExtra(CURRENCY_BUNDLE_KEY)!!
            rate = intent.getFloatExtra(RATE_BUNDLE_KEY, 0f)
        }
        baseCurrencyEditText = findViewById(R.id.et_base_currency)
        baseCurrencyEditText.addTextChangedListener(this)
        targetCurrencyTextView = findViewById(R.id.tv_target_currency)
        targetCurrencyTextView.text = currency
        targetRateTextView = findViewById(R.id.tv_target_rate)
        targetRateTextView.text = String.format("%.2f", rate)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.converter)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        }
        else {
            toolbar.navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
        }
        if(baseCurrencyEditText.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENCY_BUNDLE_KEY, currency)
        outState.putFloat(RATE_BUNDLE_KEY, rate)
        super.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    //TextWatcher
    override fun afterTextChanged(editable: Editable) {
        val calculatedRate = rate * baseCurrencyEditText.cleanDoubleValue
        targetRateTextView.text = String.format("%.2f", calculatedRate)
    }

    override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
    }
}