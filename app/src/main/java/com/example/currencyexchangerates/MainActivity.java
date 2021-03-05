package com.example.currencyexchangerates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        spinner1 = findViewById(R.id.Currency1);
        spinner2 = findViewById(R.id.Currency2);

        SpinnerAdapter mAdapter = new SpinnerAdapter(this, countryList);
        spinner1.setAdapter(mAdapter);
        spinner2.setAdapter(mAdapter);


        spinner1.setDropDownVerticalOffset(160);
        spinner1.setSelection(12);
        spinner2.setSelection(5);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int x = 0;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getItemAtPosition(position);
                String select = country.getCountryName().substring(country.getCountryName().length()-3) + " selected!";
                if(x>0)
                    Toast.makeText(getApplicationContext(), select, Toast.LENGTH_SHORT).show();
                x = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int x = 0;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getItemAtPosition(position);
                String select = country.getCountryName().substring(country.getCountryName().length()-3) + " selected!";
                if(x>0)
                    Toast.makeText(getApplicationContext(), select, Toast.LENGTH_SHORT).show();
                x = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        convert = findViewById(R.id.convert);
        result = findViewById(R.id.result);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert.setClickable(false);

                Country country = (Country) spinner1.getSelectedItem();
                Country country2 = (Country) spinner2.getSelectedItem();
                String makeThisToast =
                        "Getting current rates for "
                                + country.getCountryName().substring(country.getCountryName().length()-3)
                                + " to "
                                + country2.getCountryName().substring(country2.getCountryName().length()-3);

                Toast.makeText(getApplicationContext(), makeThisToast, Toast.LENGTH_SHORT).show();

                final android.os.Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(this::allowAccess, 1000);
            }

            private void allowAccess() {
                Country country = (Country) spinner1.getSelectedItem();
                Country country2 = (Country) spinner2.getSelectedItem();
                String str = "1 " + country.getCountryName().substring(country.getCountryName().length()-3)
                        + "  =  x " + country2.getCountryName().substring(country2.getCountryName().length()-3);
                result.setText(str);


//                result.setPaintFlags(result.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

                convert.setClickable(true);
            }
        });


    }

    private void initList(){
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