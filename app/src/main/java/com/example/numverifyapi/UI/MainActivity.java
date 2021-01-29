package com.example.numverifyapi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.numverifyapi.API.IService;
import com.example.numverifyapi.API.MobileClient;
import com.example.numverifyapi.Data.DatabaseMobile;
import com.example.numverifyapi.Data.Validation;
import com.example.numverifyapi.Pojo.MobileModel;
import com.example.numverifyapi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements onAddTextViewCustomListener {


    DatabaseMobile databaseMobile;
    Button historyButton,validationButton;
    EditText numberInputEt;
    TextView responseTv;
    MobileClient mobileClient;
    onAddTextViewCustomListener onAddTextViewCustomListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numberInputEt = findViewById(R.id.et_input);
        historyButton = findViewById(R.id.history_btn);
        validationButton = findViewById(R.id.validation_btn);
        responseTv = findViewById(R.id.response);
        databaseMobile = new DatabaseMobile(this);
        mobileClient = new MobileClient(this);



        //Validation Button to validate input and retrofit method

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseTv.setText("");
                String phone = numberInputEt.getText().toString();
                if (Validation.validate(phone) && !phone.equals("")){
                    databaseMobile.addData(phone);
                    mobileClient.retrofit(phone);
                }
                else {
                    responseTv.setText("");
                    Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //History button to view history of data

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryDataActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onAddText(Response<MobileModel> text) {
                        responseTv.setText(String.valueOf("{ valid:"+text.body().isValid())+", number:"+text.body().getNumber()+
                        ", local_format:"+text.body().getLocal_format()+", international_format:"+text.body().getInternational_format()
                        +", country_prefix:"+text.body().getCountry_prefix()+", country_code:"+text.body().getCountry_code()
                        +", country_name:"+text.body().getCountry_name()+", location:"+text.body().getLocation()+", carrier:"+text.body().getCarrier()
                        +", line_type:"+text.body().getLine_type()+" }");
    }




}
