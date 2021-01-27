package com.example.numverifyapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.numverifyapi.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);

        String contact = getItem(position);
        tvPhone.setText(String.valueOf(contact));
        return convertView;
    }
}
