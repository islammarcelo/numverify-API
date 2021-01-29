package com.example.numverifyapi.UI;

import com.example.numverifyapi.Pojo.MobileModel;

import retrofit2.Response;

public interface onAddTextViewCustomListener {
    void onAddText(Response<MobileModel> text);
}
