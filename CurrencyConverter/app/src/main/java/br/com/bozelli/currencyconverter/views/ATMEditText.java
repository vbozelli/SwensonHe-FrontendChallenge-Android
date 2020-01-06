package br.com.bozelli.currencyconverter.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import java.text.NumberFormat;
import java.util.Locale;

public class ATMEditText extends AppCompatEditText {

    private String current = "";
    private ATMEditText editText = ATMEditText.this;

    //Variables
    private String Currency = "";
    private String Separator = ",";
    private Boolean Spacing = false;
    private Boolean Delimiter = false;
    private Boolean Decimals = true;

    //Constructors
    public ATMEditText(Context context) {
        super(context);
        init();
    }

    public ATMEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ATMEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //Methods
    public void init() {

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().equals(current)) {
                    editText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[$,.]", "").replaceAll(Currency, "").replaceAll("\\s+", "");
                    if (cleanString.length() != 0) {
                        try {
                            String currencyFormat = "";
                            if (Spacing) {
                                if (Delimiter) {
                                    currencyFormat = Currency + ". ";
                                }
                                else {
                                    currencyFormat = Currency + " ";
                                }
                            }
                            else {
                                if (Delimiter) {
                                    currencyFormat = Currency + ".";
                                }
                                else {
                                    currencyFormat = Currency;
                                }
                            }
                            double parsed;
                            int parsedInt;
                            String formatted;
                            if (Decimals) {
                                parsed = Double.parseDouble(cleanString);
                                formatted = NumberFormat.getCurrencyInstance().format((parsed / 100)).replace(NumberFormat.getCurrencyInstance().getCurrency().getSymbol(), currencyFormat);
                            }
                            else {
                                parsedInt = Integer.parseInt(cleanString);
                                formatted = currencyFormat + NumberFormat.getNumberInstance(Locale.US).format(parsedInt);
                            }
                            current = formatted;
                            if (!Separator.equals(",") && !Decimals) {
                                editText.setText(formatted.replaceAll(",", Separator));
                            }
                            else {
                                editText.setText(formatted);
                            }
                            editText.setSelection(formatted.length());
                        }
                        catch (NumberFormatException e) {

                        }
                    }
                    editText.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public double getCleanDoubleValue() {
        double value = 0.0;
        if (Decimals) {
            value = Double.parseDouble(editText.getText().toString().replaceAll("[$,]", "").replaceAll(Currency, ""));
        }
        else {
            String cleanString = editText.getText().toString().replaceAll("[$,.]", "").replaceAll(Currency, "").replaceAll("\\s+", "");
            try {
                value = Double.parseDouble(cleanString);
            }
            catch (NumberFormatException e) {
            }
        }
        return value;
    }

    public int getCleanIntValue() {
        int value = 0;
        if (Decimals) {
            double doubleValue = Double.parseDouble(editText.getText().toString().replaceAll("[$,]", "").replaceAll(Currency, ""));
            value = (int) Math.round(doubleValue);
        }
        else {
            String cleanString = editText.getText().toString().replaceAll("[$,.]", "").replaceAll(Currency, "").replaceAll("\\s+", "");
            try {
                value = Integer.parseInt(cleanString);
            }
            catch (NumberFormatException e) {
            }
        }
        return value;
    }

    public void setDecimals(boolean value) {
        this.Decimals = value;
    }

    public void setCurrency(String currencySymbol) {
        this.Currency = currencySymbol;
    }

    public void setSpacing(boolean value) {
        this.Spacing = value;
    }

    public void setDelimiter(boolean value) {
        this.Delimiter = value;
    }

    public void setSeparator(String value) {
        this.Separator = value;
    }
}