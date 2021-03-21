package com.example.currencyexchangerates;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RatesDataService {
    public static final String TAG = "Currency Data Service";
    public static final String QUERY_FOR_RATES = "https://api.ratesapi.io/api/latest?";
    Context context;

    public RatesDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String value);
    }

    public void getConversionRate(String base, String symbols, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_RATES + "base=" + base + "&" + "symbols=" + symbols;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject jsonObject = response.getJSONObject("rates");
                        Double conversionRate = jsonObject.getDouble(symbols);
                        volleyResponseListener.onResponse(String.format("%.3f", conversionRate));
                    } catch (JSONException e) {
                        volleyResponseListener.onError("Something's wrong!");
                    }
                }, error -> Log.e("Rates API", "ERROR ERROR ERROR"));

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
