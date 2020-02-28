package com.rebelworks.network;

import com.rebelworks.model.request.LoginRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {

    @POST("login")
    Call<ResponseBody> requestLogin(@Body LoginRequest loginRequest);

}
