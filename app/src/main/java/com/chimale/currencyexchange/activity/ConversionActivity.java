package com.chimale.currencyexchange.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chimale.currencyexchange.R;
import com.chimale.currencyexchange.model.Currencies;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversionActivity extends AppCompatActivity {

    Double btc_rate;
    Double eth_rate;
    int index;
    Double inputValue, btcOutputValue, ethOutputValue;
    String btc, eth;


    TextView btcTextValue, ethTextValue, conversionCurrencyName;
    Button convertButton;
    CircleImageView conversionFlag;
    EditText conversionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        btcTextValue = (TextView) findViewById(R.id.btc_text_value);
        ethTextValue = (TextView) findViewById(R.id.eth_text_value);
        conversionCurrencyName = (TextView) findViewById(R.id.conversion_currency_name);
        conversionInput = (EditText) findViewById(R.id.conversion_input);
        conversionFlag = (CircleImageView) findViewById(R.id.conversion_flag);
        convertButton = (Button) findViewById(R.id.convert_button);

        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        Log.d("index ", String.valueOf(index));
        btc_rate = bundle.getDouble("btc_rate");
        eth_rate = bundle.getDouble("eth_rate");
        Log.d("index btc", String.valueOf(btc_rate));
        Log.d("index eth", String.valueOf(eth_rate));

        Picasso.with(getApplicationContext()).load(Currencies.getImageResourceId(index)).into(conversionFlag);
        conversionCurrencyName.setText(Currencies.Countries[index]);

        btc = ("Ƀ ")+(String.valueOf(btc_rate));
        eth = ("Ξ ")+(String.valueOf(eth_rate));

        btcTextValue.setText(btc);
        ethTextValue.setText(eth);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    inputValue = Double.parseDouble(conversionInput.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if (inputValue != null) {
                    convert(inputValue);
                }

            }
        });

    }

    private void convert(Double inputValue) {
        btcOutputValue = inputValue / btc_rate;
        ethOutputValue = inputValue / eth_rate;

        btc = ("Ƀ ")+(String.format(Locale.US, "%.5f",btcOutputValue));
        eth = ("Ξ ")+(String.format(Locale.US, "%.5f",ethOutputValue));

        btcTextValue.setText(btc);
        ethTextValue.setText(eth);
    }
}
