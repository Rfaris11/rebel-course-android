package com.rebelworks.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAPI {

    private final static String HOST = "http://rebel-course.ternyata.online/api/";

    private static Retrofit retrofit;

    private static void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static Endpoint service() {
        init();
        return retrofit.create(Endpoint.class);
    }
}
