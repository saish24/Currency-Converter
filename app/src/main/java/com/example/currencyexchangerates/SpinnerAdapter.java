package com.example.currencyexchangerates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Country> {

    public SpinnerAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_row, parent, false);
        }

        Country country = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.Country_Flag);
        TextView textView = convertView.findViewById(R.id.Country_Name);

        textView.setText(country.getCountryName());
        imageView.setImageResource(country.getFlagID());

        return convertView;
    }
}
