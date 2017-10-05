package com.chimale.currencyexchange.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chimale.currencyexchange.R;
import com.chimale.currencyexchange.adapter.RatesAdapter;
import com.chimale.currencyexchange.api.LoopJApiClient;
import com.chimale.currencyexchange.model.Currencies;
import com.chimale.currencyexchange.model.RateParameters;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {


    private RecyclerView ratesRecycler;

    private List<RateParameters> all_currencies_with_rates = new ArrayList<RateParameters>();

    private String currencies[] = Currencies.currenciesCodes;
    private String currency_names[] = Currencies.Countries;

    private String up_request_parameter;

    private RatesAdapter r_Adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratesRecycler=(RecyclerView)findViewById(R.id.mainActivityRecyclerView);

        defineAllCurrencies();

        LinearLayoutManager ml=new LinearLayoutManager(getApplicationContext());
        ratesRecycler.setLayoutManager(ml);

        r_Adapt=new RatesAdapter(getApplication(),all_currencies_with_rates);

        ratesRecycler.setAdapter(r_Adapt);

        getRates();

    }


    public void getRates(){

        LoopJApiClient.get("data/pricemulti?fsyms=BTC,ETH&tsyms="+up_request_parameter,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Res",response.toString());

                List<RateParameters> t=new ArrayList<RateParameters>();

                try {
                    for(int i=0;i<currencies.length;i++){
                        RateParameters p=new RateParameters();
                        p.currency_code=currencies[i];
                        p.currency_name=currency_names[i];
                        p.btc_value=response.getJSONObject("BTC").getDouble(currencies[i]);
                        p.eth_value=response.getJSONObject("ETH").getDouble(currencies[i]);
                        t.add(p);

                    }
                }catch (JSONException f){
                    f.printStackTrace();
                }

                r_Adapt.updateData(t);

            }
        });

    }


    public void defineAllCurrencies(){

        StringBuilder s=new StringBuilder();
        for(int i=0;i<currencies.length;i++){
            RateParameters p=new RateParameters();
            p.currency_code=currencies[i];
            all_currencies_with_rates.add(p);

            if(i!=currencies.length-1){
                s.append(currencies[i]).append(",");
            }else{
                s.append(currencies[i]);
            }

        }

        up_request_parameter=s.toString();

    }


}
