package com.rebelworks.network;

import com.rebelworks.course.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAPI {

    private static Retrofit retrofit;

    private static void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static Endpoint service() {
        init();
        return retrofit.create(Endpoint.class);
    }
}
