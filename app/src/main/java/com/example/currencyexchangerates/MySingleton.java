package com.example.currencyexchangerates;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MySingleton {
    private static MySingleton instance = null;
    private static Context ctx;
    private RequestQueue requestQueue;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        return (instance == null) ? new MySingleton(context): instance;
    }

    public RequestQueue getRequestQueue() {
        return (requestQueue == null) ? Volley.newRequestQueue(ctx.getApplicationContext()) : requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
