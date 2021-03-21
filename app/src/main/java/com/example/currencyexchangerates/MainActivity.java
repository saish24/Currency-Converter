package com.example.currencyexchangerates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Country> countryList;
    private Spinner spinner1, spinner2;
    private TextView convert, result;

    public static final String TAG = "Currency Main";
    private String base = "";
    private String symbols = "";
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        initList();

        spinner1 = findViewById(R.id.Currency1);
        spinner2 = findViewById(R.id.Currency2);

        SpinnerAdapter mAdapter = new SpinnerAdapter(this, countryList);

        spinner1.setAdapter(mAdapter);
        spinner2.setAdapter(mAdapter);

        spinner1.setDropDownVerticalOffset(160);
        spinner1.setSelection(1);
        spinner2.setSelection(2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = countryList.get(position).getCountryName();
                base = temp.substring(temp.length() - 3);
                Log.v(TAG, base);
                result.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = countryList.get(position).getCountryName();
                symbols = temp.substring(temp.length() - 3);
                Log.v(TAG, symbols);
                result.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convert = findViewById(R.id.convert);
        result = findViewById(R.id.result);


        convert.setOnClickListener(v -> {
            if (checkForNOInternet()) return;
//            Toast.makeText(getApplicationContext(), "Getting current rates for " + base + " to " + symbols, Toast.LENGTH_SHORT).show();

            RatesDataService ratesDataService = new RatesDataService(ctx);
            convert.setClickable(false);
            ratesDataService.getConversionRate(base, symbols, new RatesDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String value) {
                    String str = "1 " + base + "  =  " + value + " " + symbols;
                    Log.v(TAG, str);
                    result.setText(str);
                    convert.setClickable(true);
                }
            });
        });
    }

    private boolean checkForNOInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() == null) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void initList() {
        countryList = new ArrayList<>();
        countryList.add(new Country(R.drawable.bulgaria, "Bulgarian Lev BGN"));
        countryList.add(new Country(R.drawable.czech, "Czech Koruna CZK"));
        countryList.add(new Country(R.drawable.danish, "Danish Krone DKK"));
        countryList.add(new Country(R.drawable.britain, "Pound Sterling GBP"));
        countryList.add(new Country(R.drawable.hungary, "Hungarian Forint HUF"));
        countryList.add(new Country(R.drawable.japan, "Japanese Yen JPY"));
        countryList.add(new Country(R.drawable.poland, "Polish Zloty PLN"));
        countryList.add(new Country(R.drawable.romania, "Romanian Leu RON"));
        countryList.add(new Country(R.drawable.sweden, "Swedish Krona SEK"));
        countryList.add(new Country(R.drawable.switzerland, "Swiss Franc CHF"));
        countryList.add(new Country(R.drawable.iceland, "Icelandic Krona ISK"));
        countryList.add(new Country(R.drawable.norway, "Norwegian Krone NOK"));
        countryList.add(new Country(R.drawable.croatia, "Croatian Kuna HRK"));
        countryList.add(new Country(R.drawable.russia, "Russian Rouble RUB"));
        countryList.add(new Country(R.drawable.turkey, "Turkish Lira TRY"));
        countryList.add(new Country(R.drawable.austalia, "Australian Dollar AUD"));
        countryList.add(new Country(R.drawable.brazil, "Brazilian Real BRL"));
        countryList.add(new Country(R.drawable.canada, "Canadian Dollar CAD"));
        countryList.add(new Country(R.drawable.china, "Chinese Yuan Renminbi CNY"));
        countryList.add(new Country(R.drawable.hongkong, "Hong Kong Dollar HKD"));
        countryList.add(new Country(R.drawable.indonesia, "Indonesian Rupiah IDR"));
        countryList.add(new Country(R.drawable.israel, "Israeli Shekel ILS"));
        countryList.add(new Country(R.drawable.india, "Indian Rupee INR"));
        countryList.add(new Country(R.drawable.southkorea, "South Korean Won KRW"));
        countryList.add(new Country(R.drawable.mexico, "Mexican Peso MXN"));
        countryList.add(new Country(R.drawable.malaysia, "Malaysian Ringgit MYR"));
        countryList.add(new Country(R.drawable.newzeland, "New Zealand Dollar NZD"));
        countryList.add(new Country(R.drawable.philippines, "Philippine Peso PHP"));
        countryList.add(new Country(R.drawable.singapore, "Singapore Dollar SGD"));
        countryList.add(new Country(R.drawable.thailand, "Thai Baht THB"));
        countryList.add(new Country(R.drawable.usa, "US Dollar USD"));
        countryList.add(new Country(R.drawable.southafrica, "South African Rand ZAR"));
    }

}