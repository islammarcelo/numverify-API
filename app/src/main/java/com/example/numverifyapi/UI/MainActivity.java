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
import com.example.numverifyapi.Data.DatabaseMobile;
import com.example.numverifyapi.Data.Validation;
import com.example.numverifyapi.Pojo.MobileModel;
import com.example.numverifyapi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    DatabaseMobile databaseMobile;
    Button historyButton,validationButton;
    EditText numberInputEt;
    TextView responseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numberInputEt = findViewById(R.id.et_input);
        historyButton = findViewById(R.id.history_btn);
        validationButton = findViewById(R.id.validation_btn);
        responseTv = findViewById(R.id.response);
        databaseMobile = new DatabaseMobile(this);
        //Validation Button to validate input and retrofit method

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseTv.setText("");
                String phone = numberInputEt.getText().toString();
                if (Validation.validate(phone) && !phone.equals("")){
                    databaseMobile.addData(phone);
                    retrofit(phone);
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


    /**
     * Retrofit method to connect to web Servies take phone number as parameter
     * @param phone
     */
    public  void retrofit(String phone) {

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(IService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IService apiInterface = retrofit.create(IService.class);

        Call<MobileModel> call = apiInterface.getPhone(IService.API_KEY, phone);

        call.enqueue(new Callback<MobileModel>() {
            @Override
            public void onResponse(Call<MobileModel> call, Response<MobileModel> response) {

                responseTv.setText(String.valueOf("{ valid:"+response.body().isValid())+", number:"+response.body().getNumber()+
                        ", local_format:"+response.body().getLocal_format()+", international_format:"+response.body().getInternational_format()
                         +", country_prefix:"+response.body().getCountry_prefix()+", country_code:"+response.body().getCountry_code()
                         +", country_name:"+response.body().getCountry_name()+", location:"+response.body().getLocation()+", carrier:"+response.body().getCarrier()
                          +", line_type:"+response.body().getLine_type()+" }");
            }
            @Override
            public void onFailure(Call<MobileModel> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
