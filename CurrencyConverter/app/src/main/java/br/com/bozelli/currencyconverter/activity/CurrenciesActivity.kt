package br.com.bozelli.currencyconverter.activity

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bozelli.currencyconverter.R
import br.com.bozelli.currencyconverter.adapter.CurrenciesAdapter
import br.com.bozelli.currencyconverter.interfaces.CurrenciesRatesRequestListener
import br.com.bozelli.currencyconverter.model.CurrenciesRates
import br.com.bozelli.currencyconverter.model.CurrenciesRatesResponse
import br.com.bozelli.currencyconverter.networking.CurrenciesRatesRequest
import br.com.bozelli.currencyconverter.utils.connectedToInternet
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CurrenciesActivity: AppCompatActivity(), CurrenciesRatesRequestListener, Runnable {
    //Constants
    private val CURRENCIES_RATES_BUNDLE_KEY = "currenciesRates"

    //Variables
    private lateinit var currenciesRates: CurrenciesRates
    private lateinit var progressConstraintLayout: ConstraintLayout
    private lateinit var currenciesRecyclerView: RecyclerView
    private var message: String? = null

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)
        progressConstraintLayout = findViewById(R.id.cl_progress)
        currenciesRecyclerView = findViewById(R.id.rv_currencies)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        }
        else {
            toolbar.navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
        }
        if (savedInstanceState != null) {
            currenciesRates = savedInstanceState.getParcelable(CURRENCIES_RATES_BUNDLE_KEY)!!
            showRecyclerView()
        }
        else if (connectedToInternet(this)) {
            progressConstraintLayout.visibility = VISIBLE
            CurrenciesRatesRequest(this).getCurrenciesRates()
        }
        else {
            val dialogBuilder = MaterialAlertDialogBuilder(this).setCancelable(true).setTitle(R.string.attention).setMessage(R.string.not_connected_internet).setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert)
            showDialog(dialogBuilder)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(CURRENCIES_RATES_BUNDLE_KEY, currenciesRates)
        super.onSaveInstanceState(outState)
    }

    //Methods
    private fun showRecyclerView() {
        currenciesRecyclerView.visibility = VISIBLE
        currenciesRecyclerView.layoutManager = LinearLayoutManager(this)
        val currenciesAdapter = CurrenciesAdapter(currenciesRates, this)
        currenciesRecyclerView.adapter = currenciesAdapter
    }

    private fun showDialog(dialogBuilder: MaterialAlertDialogBuilder) {
        var showDialog = !isFinishing
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            showDialog = showDialog && !isDestroyed
        }
        if (showDialog) {
            try {
                dialogBuilder.show()
            }
            catch (e: Exception) {
            }
        }
    }

    //CurrenciesRatesRequestListener
    override fun error(message: String) {
        this.message = message
        runOnUiThread(this)
    }

    override fun sucess(response: CurrenciesRatesResponse) {
        currenciesRates = response.rates
        runOnUiThread(this)
    }

    //Runnable
    override fun run() {
        progressConstraintLayout.visibility = GONE
        if (message != null) {
            val dialogBuilder = MaterialAlertDialogBuilder(this).setCancelable(true).setTitle(R.string.attention).setMessage(message).setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert)
            showDialog(dialogBuilder)
        }
        else {
            showRecyclerView()
        }
    }
}