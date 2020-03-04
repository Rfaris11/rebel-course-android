package com.rebelworks.network;

import com.rebelworks.model.request.LoginRequest;
import com.rebelworks.model.request.RegisterRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {

    @POST("login")
    Call<ResponseBody> requestLogin(@Body LoginRequest request);

    @POST("register")
    Call<ResponseBody> requestRegister(@Body RegisterRequest request);

}
