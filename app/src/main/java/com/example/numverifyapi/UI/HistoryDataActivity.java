package com.example.numverifyapi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.numverifyapi.Adapter.ListAdapter;
import com.example.numverifyapi.Data.DatabaseMobile;
import com.example.numverifyapi.R;

import java.util.ArrayList;

public class HistoryDataActivity extends AppCompatActivity {

    ListView numbersList;
    DatabaseMobile databaseMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_data);

        numbersList = findViewById(R.id.phone_List);
        databaseMobile = new DatabaseMobile(this);
        ArrayList<String> arrayList = databaseMobile.getAllContacts();

        ListAdapter listAdapter = new ListAdapter(this, R.layout.item, arrayList);
        numbersList.setAdapter(listAdapter);


    }
}
