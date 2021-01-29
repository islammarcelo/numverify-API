package com.example.numverifyapi.API;

import android.util.Log;

import com.example.numverifyapi.Pojo.MobileModel;
import com.example.numverifyapi.UI.onAddTextViewCustomListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MobileClient {

    private onAddTextViewCustomListener onAddTextViewCustomListener;


    public MobileClient(onAddTextViewCustomListener onAddTextViewCustomListener) {
        this.onAddTextViewCustomListener =onAddTextViewCustomListener;

    }

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

                onAddTextViewCustomListener.onAddText(response);

//                responseTv.setText(String.valueOf("{ valid:"+response.body().isValid())+", number:"+response.body().getNumber()+
//                        ", local_format:"+response.body().getLocal_format()+", international_format:"+response.body().getInternational_format()
//                        +", country_prefix:"+response.body().getCountry_prefix()+", country_code:"+response.body().getCountry_code()
//                        +", country_name:"+response.body().getCountry_name()+", location:"+response.body().getLocation()+", carrier:"+response.body().getCarrier()
//                        +", line_type:"+response.body().getLine_type()+" }");
            }
            @Override
            public void onFailure(Call<MobileModel> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
